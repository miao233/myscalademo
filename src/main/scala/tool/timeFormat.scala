package tool

import java.text.SimpleDateFormat

object timeFormat {
  def main(args: Array[String]): Unit = {
    val timeFormat = new SimpleDateFormat("yyyyMMdd")
    val timeStamp = timeFormat.parse("20200707").getTime //日期转时间戳
    println(timeStamp)
    println(timeFormat.format(timeStamp)) //时间戳转日期
  }
}
