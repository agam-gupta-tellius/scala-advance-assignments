object Assignment1 extends App {
  private def funWithImplicitParameter(x: Int)(implicit y: Int) = x+y

  implicit val payload: Int = 100
  val res = funWithImplicitParameter(11)
  println(res)
}
