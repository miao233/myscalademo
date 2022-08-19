package sparkTest

import scala.math._

object Demo {
  def main(args: Array[String]): Unit = {
    val data1 = Array(Array(1.2,2.3),Array(3.1,8.9))
    val data2 = Array(Array(4.2,3.4),Array(7.4,8.9))
    val res = data1.zip(data2)
    val res2 = res.map(r => {
      val d1 = r._1
      val d2 = r._2
      val up = d1.zip(d2).map(item => item._1 * item._2).sum
      val under = sqrt(d1.map(x => x*x).sum) + sqrt(d2.map(y => y*y).sum)
      up/under
    })
    res2.foreach(println)
//    val conf = new SparkConf().setMaster("local").setAppName("test")
//    val spark = SparkSession.builder().config(conf).getOrCreate()
//    spark.sparkContext.setLogLevel("WARN")
//    import spark.implicits._

//    val list = List(("a",1),("a",2),("a",3),
//      ("b",1),("b",2),("c",1))
//
//    val data = spark.sparkContext.parallelize(list)
//    val res = data.reduceByKey((x,_) => x)
//
//    data.foreach(println)
//    println("=========")
//    res.foreach(println)
  }
}