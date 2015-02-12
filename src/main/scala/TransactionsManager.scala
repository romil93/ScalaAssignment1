import scala.collection.immutable.IndexedSeq

/**
 * Created by indix on 12/2/15.
 */
class Processor {
  //This is the processor class and holds the variables. x can be edited by only agents. saved can be edited only by admins
  var x: List[Any] = Nil
  var saved: List[Any] = Nil
}

class ProcessorAgent(p: Processor){
  def respond(value: String): List[Any] = {
    value :: p.x
  }
}

class command(com: String) {
  com match {
    case "commit"+ z => {

    }
  }
}

case class request(req: String) {

}

case object commit{

}

case object rollback {

}

trait Agent {
  //This trait is used to make sure that Agent objects have access to at least the respond function
  def edit(x: Any): Any
}

class Agents(p: ProcessorAgent) extends Agent {
  //This class is where the functionality of the agents class is specified
  def edit(x: Any): Any = { //calls the respond function of the Processor class
    p.respond(x)
  }
}

trait Administrator {
  //Gives commit and rollback definition
  def commitByAdmin(): Any
  def rollbackByAdmin(): Any
}

class Admin(p: Processor) extends Administrator{
  //Definitions of the Administrator class
  def commitByAdmin(): Any = { //calls commit function of the processor
    p.commit()
  }

  def rollbackByAdmin(): Any = { //calls rollback function of the processor
    p.rollback()
  }
}