package mldemo

/**
 * Author : gocn
 * Time   : 2022/5/6
 * */

import org.apache.spark.SparkContext
import org.apache.spark.mllib.{linalg, stat}

object dataLoadTest extends App {
  var sc = new SparkContext("local", "analyse")
  var txt = sc.textFile("data/beijing.txt")
  // foreach 打印
  // 查看数据
  val array = txt.flatMap(_.split(",")).take(10)
  // 每个都是RDD
  val data = txt.flatMap(_.split(",")).map(value => linalg.Vectors.dense(value.toDouble))
  sc.setLogLevel("WARN")
  val res = stat.Statistics.colStats(data)
  array.foreach(println)

  println(res.max)
  println(res.min)
  println(res.mean)
  println(res.variance)


  //  println(txt.count())
}
