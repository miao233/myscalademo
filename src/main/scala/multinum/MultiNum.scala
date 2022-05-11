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
    val startTime: Long = System.currentTimeMillis
    val finalRes = preData.join(preData)
      .filter(r => r._2._1.time < r._2._2.time
        && r._2._2.time - r._2._1.time < 10
        && r._2._1.num != r._2._2.num)
      .map(r => multi(r._1.subImei, r._1.event, r._2._1.time, r._2._2.time, r._2._1.num, r._2._2.num))
    //      .foreach(println)
    finalRes.foreach(println)
    println(System.currentTimeMillis - startTime)
    println("==============RESULT2===============")
    val startTime2: Long = System.currentTimeMillis
    val finalRes2 = data.groupBy(r => (r.subImei, r.event))
      .flatMap(r => {
        val preData = r._2.toArray
        val res = preData.flatMap(r => {
          windowCreate(preData, r.time)
            .filter(_.num != r.num)
            .map(w => multi(r.subImei, r.event, r.time, w.time, r.num, w.num))
        }).filterNot(r => r.time1 == r.time2 && r.num1 < r.num2)
          .map(r => {
            if (r.num1 < r.num2) {
              multi(r.subImei, r.event, r.time1, r.time2, r.num1, r.num2)
            } else {
              multi(r.subImei, r.event, r.time2, r.time1, r.num2, r.num1)
            }
          })
        res
      }
      )
    finalRes2.foreach(println)
    println(System.currentTimeMillis - startTime2)
  }

  case class lte(num: String, event: String, cp: String, time: Long, subImei: String)

  def windowCreate(data: Array[lte], timeStamp: Long): Array[lte] = {
    data.filter(r => r.time >= timeStamp && r.time < timeStamp + 10)
  }

  case class multi(subImei: String, event: String, time1: Long, time2: Long, num1: String, num2: String)


}


