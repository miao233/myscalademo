/**
 * Author: gocn
 * 2022/8/19 10:50
 * Desc: ${DESCRIPTION}
 * History:
 * <time> <desc>
 * */
object testing extends App {
  //  val str = "hphm##color"
  //  val resSplit = str.split("#")
  //  val union = Seq("222","","333").mkString("#")
  //  println(union.split("#")(1))
  val str = "123,456,789"
  val items = str.split(",")
  val len = items.length
  def showItems(strings: String*): Unit =
    strings.foreach(println)


  val res = items.mkString(" string,") + " string"
  println(res)
  println("======")
  import hellodemo.tool.Edit._
  val res2 = editDist("宝地广场","宝地广场东门的摄像头")
  println(res2)
}
