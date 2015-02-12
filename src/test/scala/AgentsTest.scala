import org.scalatest.FlatSpec
import org.scalatest.Matchers._
/**
 * Created by indix on 12/2/15.
 */
class AgentsTest extends FlatSpec {
  "AgentsTest" should "make sure that an agents has access to the respond function" in {
    val p = new Processor
    val A = new Agents(p)
    val B = new Agents(p)
    val C = new Agents(p)

    A.edit(10) should be(10)
    B.edit(20) should be(20)
    C.edit(30) should be(30)

    //There can be no fail test case as there is only one function it is given access to.
  }
}
