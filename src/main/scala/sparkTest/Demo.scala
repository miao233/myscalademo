package sparkTest

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Demo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("test")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    import spark.implicits._

    val list = List(("a",1),("a",2),("a",3),
      ("b",1),("b",2),("c",1))

    val data = spark.sparkContext.parallelize(list)
    val res = data.reduceByKey((x,_) => x)

    data.foreach(println)
    println("=========")
    res.foreach(println)
  }
}