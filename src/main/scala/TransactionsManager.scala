class Processor1 {
  //This is the processor class and holds the variables. x can be edited by only agents. saved can be edited only by admins
  var x: List[String] = List()
  var saved: List[String] = List()
  def process(command: Request) = {
    command match {
      case Commit => saved = x
      case Rollback => x = saved
      case Transaction(value) => x = value :: x
    }
  }
}

sealed trait Request
case object Commit extends Request
case object Rollback extends Request
case class Transaction(value: String) extends Request

trait Actor {
  def submit(request: Request): Unit
}
case class User(name: String, p: Processor1) extends Actor {
  def submit(request: Request): Unit = {
    request match {
      case Transaction(value) => p.process(request)
      case _ => throw new IllegalArgumentException("Cannot perform operations like Commit and Rollback")
    }
  }
}
case class Admin(name: String, p: Processor1) extends Actor {
  def submit(request: Request): Unit = {
    request match {
      case Commit     => p.process(request)
      case Rollback   => p.process(request)
      case _          => throw new IllegalArgumentException("Cannot perform operations apart from Commit and Rollback")
    }
  }
}

object test{
  val p = new Processor1
  val A = new User("A", p)
  val B = new User("B", p)
  val C = new User("C", p)

  val admin1 = new Admin("Admin 1", p)
  val admin2 = new Admin("Admin 2", p)

  A.submit(Transaction("push 5"))
  A.submit(Transaction("get 10"))
  admin1.submit(Commit)
}