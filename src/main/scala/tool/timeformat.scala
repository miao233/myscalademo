package tool

import java.text.SimpleDateFormat

object timeformat {
  def main(args: Array[String]): Unit = {
    val timeFormat = new SimpleDateFormat("yyyyMMddHH")
    println(timeFormat.parse("2020070719").getTime)
  }
}
