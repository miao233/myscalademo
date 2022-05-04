package mldemo

import org.apache.spark.mllib.{linalg, stat}
import org.apache.spark.SparkContext

/**
 * Author : gocn
 * Time   : 2022/5/4
 **/


object valueTest {
  def main(args: Array[String]): Unit = {
    //该特征与结果不相关。
    //因此，当p值比较小时，证明该假设出现的概率很低，从而有更大的把握拒绝假设，故而我们得出了：
    object chi2_test extends App {

      var sc = new SparkContext("local", "chi2_test")
      val Predate = linalg.Matrices.dense(2, 2, Array(129, 19, 147, 10))
      println(Predate)

// todo 版本适配 stat类中现在找不到chiSqTest
      // 下面的检验不能通过
//      println(stat.Statistics.chiSqTest(data))


    }

  }
}
