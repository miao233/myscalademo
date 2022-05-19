package mldemo

import org.apache.spark.SparkContext
import org.apache.spark.mllib.{linalg, stat}

/**
 * Author : gocn
 * Time   : 2022/5/4
 **/


object valueTest {
  def main(args: Array[String]): Unit = {
    //该特征与结果不相关。
    var sc = new SparkContext("local", "chi2_test")
    val Predate = linalg.Matrices.dense(2, 2, Array(129, 19, 147, 10))
    println(Predate)

    // 检验调通，数据类型不适配导致
    println(stat.Statistics.chiSqTest(Predate))

    /**
     * result demo
     * 129.0  147.0
     * 19.0   10.0
     * Chi squared test summary:
     * method: pearson
     * degrees of freedom = 1
     * statistic = 3.70466850087609
     * pValue = 0.05426044887517323
     * Low presumption against null hypothesis: the occurrence of the outcomes is statistically independent..
     */

    //    }

  }
}
