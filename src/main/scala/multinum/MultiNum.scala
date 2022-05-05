package multinum

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object MultiNum {
  def main(args: Array[String]): Unit = {
    //创建一个SparkSession，在Spark2.1中，SparkSession包括了SparkContext，若想使用SparkContext，用sc.sparkContext即可
    val conf = new SparkConf().setMaster("local").setAppName("multi")
    val sc = SparkSession.builder().config(conf).getOrCreate()
    //创建intRDD

    // case class Rating(str: String, str1: String, timeS: Int, str2: String)

    val parseRating = (string: String) => {
      val stringArray = string.split(",")
      (stringArray(0), stringArray(1), stringArray(2).toInt, stringArray(3))
      // (13800138000,2,1651497821,85931123)
    }

    //    import spark.implicits._

    val data = sc.sparkContext.textFile("./data/multi/phone.csv")
      .map(parseRating)
    data.foreach(println)
    //    data.map(row =>{
    //      data.row(1)
    //    }
    //    )

  }
  // todo

}

/** test data
 * 13800138000,2,1651497821,85931123
 * 13800138001,2,1651497829,85931123
 * 13800138002,2,1651497831,85931123
 * 13800138003,2,1651497832,85931124
 * 13800138004,2,1651497841,85931123
 * 13800138001,2,1651497841,85931123
 * 13800138002,2,1651497846,85931133
 * 13800138003,2,1651497851,85931133
 * 13800138004,2,1651497852,85931123
 * 13800138005,2,1651497856,85931123
 */

