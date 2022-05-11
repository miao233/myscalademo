package multinum

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import java.text.SimpleDateFormat

object MultiNum {

  def main(args: Array[String]): Unit = {
    //创建一个SparkSession，在Spark2.1中，SparkSession包括了SparkContext，若想使用SparkContext，用sc.sparkContext即可
    val conf = new SparkConf().setMaster("local").setAppName("multi")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    // case class Rating(str: String, str1: String, timeS: Int, str2: String)
    val timeFormat = new SimpleDateFormat("yyyyMMddHH")
    val parseRating = (string: String) => {
      val stringArray = string.split(",")
      lte(stringArray(0),
        stringArray(1),
        timeFormat.format(stringArray(2).toInt * 1000),
        stringArray(2).toInt,
        stringArray(3))
      // (13800138000,2,1651497821,85931123)
    }

    //    import spark.implicits._

    val data = spark.sparkContext.textFile("./data/multi/phone.csv")
      .map(parseRating)

    data.foreach(println)

    println("==============RESULT===============")

    case class index(subImei: String, event: String)

    val preData = data.map(l => {
      (index(l.subImei, l.event), l)
    })

    val finalRes = preData.join(preData)
      .filter(r => r._2._1.time < r._2._2.time
        && r._2._2.time - r._2._1.time < 10
        && r._2._1.num != r._2._2.num)
      .map(r => multi(r._1.subImei, r._1.event, r._2._1.time, r._2._2.time, r._2._1.num, r._2._2.num))
    //      .foreach(println)
    finalRes.foreach(println)
    println("==============RESULT2===============")
    val finalRes2 = data.groupBy(r => (r.subImei, r.event))
      .map(r => {
        //        val res = new ListBuffer[multi]()
        val preData = r._2.toArray

        val res = preData.map(r => {
          val window = windowCreate(preData, r.time)
            .filter(_.num != r.num)
          if (window.length > 0) {
            // todo 相同时间特殊处理
            window.map(w => multi(r.subImei, r.event, r.time, w.time, r.num, w.num))
              .filterNot(i => i.time1 == i.time2 && i.num2 > i.num1)
          }
        })
        //        res.take(res.length)
        //        for (i <- res) yield i
        res
      }
      ).filter(_.nonEmpty).reduce(_.union(_))
    finalRes2.foreach(println)
  }

  case class lte(num: String, event: String, cp: String, time: Long, subImei: String)

  def windowCreate(data: Array[lte], timeStamp: Long): Array[lte] = {
    data.filter(r => r.time >= timeStamp && r.time < timeStamp + 10)
  }

  case class multi(subImei: String, event: String, time1: Long, time2: Long, num1: String, num2: String)


}


