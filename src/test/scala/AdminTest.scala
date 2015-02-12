import org.scalatest.FlatSpec
import org.scalatest.Matchers._

/**
 * Created by indix on 12/2/15.
 */
class AdminTest extends FlatSpec {
  "AdminTest" should "test if the admin functionality of commit and rollback work properly" in {
    val p = new Processor
    val A = new Agents(p)
    val B = new Agents(p)
    val C = new Agents(p)

    val admin1 = new Admin(p)
    val admin2 = new Admin(p)

    admin1.rollbackByAdmin()
    p.commit should be(Nil)

    admin1.commitByAdmin()
    p.commit should be(Nil)

    A.edit(10) should be(10)

    admin2.commitByAdmin()
    p.commit should be(10)

    A.edit(20) should be(20)
    A.edit(30) should be(30)

    admin1.rollbackByAdmin()
    p.commit should be(10)

  }
}
