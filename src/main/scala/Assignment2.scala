object Assignment2 extends App {

  val list: List[Int] = List(1,2,3,4)

  implicit class MyList(val list: List[Int]) {
    def print(): Unit = list.foreach(println)
  }

  list.print()
}
