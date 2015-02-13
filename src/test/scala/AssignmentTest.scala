import org.scalatest.FlatSpec
import org.scalatest.Matchers._
/**
 * Created by indix on 13/2/15.
 */
class AssignmentTest extends FlatSpec {
  "AssignmentTest" should "make sure if the transaction management is maintained" in {
    val p = new Processor1
    val A = new User("A", p)
    val B = new User("B", p)
    val C = new User("C", p)

    val admin1 = new Admin("Admin 1", p)
    val admin2 = new Admin("Admin 2", p)

    A.submit(Transaction("push 5"))
    p.x should be(List("push 5"))
    admin1.submit(Commit)
    p.saved should be(List("push 5"))
  }


  "AssignmentTest" should "make sure this test always catches the exception of the Admin trespassing" in {
    val p = new Processor1
    val A = new User("A", p)
    val B = new User("B", p)
    val C = new User("C", p)

    val admin1 = new Admin("Admin 1", p)
    val admin2 = new Admin("Admin 2", p)

    val exception = intercept[IllegalArgumentException]{
      admin1.submit(Transaction("push 5"))
    }

    exception.getMessage should be("Cannot perform operations apart from Commit and Rollback")
  }


  "AssignmentTest" should "make sure this test always catches the exception of the User trespassing with Commit" in {
    val p = new Processor1
    val A = new User("A", p)
    val B = new User("B", p)
    val C = new User("C", p)

    val admin1 = new Admin("Admin 1", p)
    val admin2 = new Admin("Admin 2", p)

    val exception = intercept[IllegalArgumentException]{
      A.submit(Commit)
    }

    exception.getMessage should be("Cannot perform operations like Commit and Rollback")
  }

  "AssignmentTest" should "make sure this test always catches the exception of the User trespassing with Rollback" in {
    val p = new Processor1
    val A = new User("A", p)
    val B = new User("B", p)
    val C = new User("C", p)

    val admin1 = new Admin("Admin 1", p)
    val admin2 = new Admin("Admin 2", p)

    val exception = intercept[IllegalArgumentException]{
      A.submit(Rollback)
    }

    exception.getMessage should be("Cannot perform operations like Commit and Rollback")
  }
}
