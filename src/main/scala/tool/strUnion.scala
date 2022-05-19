package tool

object strUnion {
  def main(args: Array[String]): Unit = {
    // 字符拼接
    val event = "1,2,3"
    val tableList = "tb1,tb2,tb3"
    val sql = tableList.split(",") .filter(_.nonEmpty)   // 过滤空值
      .map(r => s"select * from $r where event in ($event)").mkString(" union all ")
    println(sql)
    println(event + tableList)
    println("".nonEmpty) //false
  }
}
