package hellodemo

/**
 * testing scala sbt env
 * 2022-04-30
 */
object hello extends TraitDemo {
  def main(args: Array[String]): Unit = {
    //    println("hello,scala")
    //    val someArray = Array()
    val helloDemo = new HelloClass
    println(helloDemo.pythonStr)
    println(helloDemo.cPlusStr)
    //    println()
    helloU(helloDemo.scalaStr)

  }
}
