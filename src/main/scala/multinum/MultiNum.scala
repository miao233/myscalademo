package multinum

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import java.text.SimpleDateFormat

object MultiNum {

  case class lte(num: String, event: String, cp: String, time: Long, subImei: String)

  def main(args: Array[String]): Unit = {
    //创建一个SparkSession，在Spark2.1中，SparkSession包括了SparkContext，若想使用SparkContext，用sc.sparkContext即可
    val conf = new SparkConf().setMaster("local").setAppName("multi")
    val sc = SparkSession.builder().config(conf).getOrCreate()
    sc.sparkContext.setLogLevel("WARN")
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

    val data = sc.sparkContext.textFile("./data/multi/phone.csv")
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
      .map(r => (r._1.subImei, r._1.event, r._2._1.time, r._2._2.time, r._2._1.num, r._2._2.num))
    //      .foreach(println)
    finalRes.foreach(println)
  }


}


