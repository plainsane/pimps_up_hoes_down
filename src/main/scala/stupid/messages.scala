package streets.stupid.messages
import akka.actor._
import scala.collection.immutable.Map
import streets.stupid.Hoe


class payment
class broke
class move
case class playa(pimp:ActorRef)
case class unemployeed(pimp: Option[ActorRef])
case class pimpfight(strength: Integer)
case class money(amount:Integer)
case class slap(force:Integer)
case class myhoes(pimp: ActorRef, hoes: Map[ActorRef, Integer])
class dead
class jealous


