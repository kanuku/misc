object Monads {
  case class Success[T](x: T) extends Try[T]
  case class Failure(ex: Throwable) extends Try[Nothing]

  abstract class Try[+T] {
    import scala.util.control.NonFatal;

    def flatMap[U](f: T => Try[U]): Try[U] = this match {
      case Success(x) =>
        try { f(x) }
        catch {
          case NonFatal(ex) => Failure(ex)
          case fail: Failure => fail
        }
    }
    def map[U](f: T => U): Try[U] = this match {
      case Success(x) => Try(f(x))
      case fail: Failure => fail
    }
  }

}
