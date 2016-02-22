package streets.stupid

import akka.actor._
import streets.stupid.messages._
import scalaz._
import scalaz.std.option._

object Driver extends App {
  val system = ActorSystem("crimedoesntpay")
  val inbox = Inbox.create(system)
  val pimp1 = system.actorOf(Props[Pimp], "playa_1")
  val pimp2 = system.actorOf(Props[Pimp], "playa_2")
  val pimp3 = system.actorOf(Props[Pimp], "playa_3")
	val hoe1 = system.actorOf(Props[Hoe], "hoe_1")
	val hoe2 = system.actorOf(Props[Hoe], "hoe_2")
	val hoe3 = system.actorOf(Props[Hoe], "hoe_3")
	hoe2 ! new unemployeed(some(pimp2))
  hoe1 ! new unemployeed(some(pimp1))
  hoe3 ! new unemployeed(some(pimp3)) 
  
  pimp1 ! playa(pimp2)
  pimp1 ! playa(pimp3)
  
  pimp2 ! playa(pimp1)
  pimp2 ! playa(pimp3)
  
  pimp3 ! playa(pimp1)
  pimp3 ! playa(pimp2)
}