package multinum

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * gocn
 * 2022-5-7
 */

//todo 号码对统计
object CountMulti {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("multiCounter")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    import spark.implicits._
    val seq = Seq(
      multi("123", "456", 123456, 123457, "2", "23512353543"),
      multi("123", "456", 123456, 123459, "2", "23512353543"),
      multi("123", "457", 123456, 123459, "2", "23512353543")
    )
//    val data = spark.sparkContext.parallelize(seq)
    val df = spark.createDataFrame(seq)
    val ds = spark.createDataset(seq)

    df.show()
    println("""===============================""")
    //    df.groupBy("num1","num2").agg(count("num1").as("cnt"))
    // 聚合统计demo
    val res = ds.groupBy("num1", "num2")
      .agg("num1" -> "count", "time1" -> "max")
      .toDF("num1", "num2", "cnt", "max")

    res.show
    println("""===============================""")

    // df转ds
    val resDs = res.as[combine]
    resDs.show


  }

  case class multi(num1: String,
                   num2: String,
                   time1: Long,
                   time2: Long,
                   event: String,
                   subImei: String
                  )

  case class combine(num1: String,
                     num2: String,
                     cnt: Long,
                     max: Long)
}
