package hellodemo

trait TraitDemo {
  def helloU(something: String): Unit = {
    println(s"Trait demo! $something")
  }
}
