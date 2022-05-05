package tool

import java.text.SimpleDateFormat

object timeformat {
  def main(args: Array[String]): Unit = {
    val timeFormat = new SimpleDateFormat("yyyyMMddHH")
    val timeStamp = timeFormat.parse("2020070719").getTime //日期转时间戳
    println(timeStamp)
    println(timeFormat.format(timeStamp)) //时间戳转日期
  }
}
