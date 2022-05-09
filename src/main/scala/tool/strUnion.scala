package tool

object strUnion {
  def main(args: Array[String]): Unit = {
    val event = "1,2,3"
    val tableList = "tb1,tb2,tb3"
    val sql = tableList.split(",")
      .map(r => s"select * from $r where event in ($event)").mkString(" union all ")
    println(sql)
  }
}
