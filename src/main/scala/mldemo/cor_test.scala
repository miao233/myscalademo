package mldemo

import org.apache.spark.SparkContext
import org.apache.spark.mllib.stat._

object cor_test {
  def main(args: Array[String]): Unit = {
    var sc = new SparkContext("local", "analyse")
    var txt = sc.textFile("data/beijing_cor.txt")
    val data = txt.flatMap(_.split(",")).map(_.toDouble)
    val year = data.filter(_ > 1000)
    val values = data.filter(_ <= 1000)
    // 相关系数
    println(Statistics.corr(year, values))
  }

}
