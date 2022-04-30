package mldemo

import org.apache.spark.SparkContext
import org.apache.spark.mllib.{linalg, stat}

object dataload {
  def main(args: Array[String]): Unit = {
    var sc = new SparkContext("local", "dataload")
    var txt = sc.textFile("./data/beijing.txt")
    // foreach 打印
    // 查看数据
    val array = txt.flatMap(_.split(",")).take(10)
    val dataSet = array.mkString(",")

    // 每个都是RDD
    val data = txt.flatMap(_.split(",")).map(value => linalg.Vectors.dense(value.toDouble))

    val res = stat.Statistics.colStats(data)

    println(dataSet)
    println(res.max)
    println(res.min)
    println(res.mean)
    println(res.variance)
  }
}
