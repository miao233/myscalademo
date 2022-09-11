package hellodemo.tool

import java.text.SimpleDateFormat

object timeFormat {
  def main(args: Array[String]): Unit = {
    val timeFormat = new SimpleDateFormat("yyyy-MM-dd")
    val timeStamp = timeFormat.parse("2020-07-07").getTime //日期转时间戳
    println(timeStamp)
    println(timeFormat.format(timeStamp)) //时间戳转日期
  }
}
