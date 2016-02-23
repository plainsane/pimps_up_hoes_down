package streets.stupid
import akka.actor._
import akka.actor.Scheduler
import streets.stupid.messages._
import streets.stupid.exceptions._
import scala.language.implicitConversions
import scala.collection.mutable.{Map => MMap}
import scala.collection.immutable.{Map => IMap}
import scala.util.Random
import scalaz._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scalaz.syntax.std.BooleanOps
import Scalaz._
import scala.language.postfixOps

class Pimp extends Actor {
  import akka.actor.SupervisorStrategy._
  import akka.actor.OneForOneStrategy
  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: Exception               => Escalate
    }
  val lies = Random
  val hoes = MMap[ActorRef, Integer]()
  var otha_playas = List[ActorRef]()
  var retrobution = Set[ActorRef]()
  var strength:Integer = Random.nextInt(100)
  context.system.scheduler.scheduleOnce(30.second, self, new jealous)
  context.system.scheduler.scheduleOnce(5.second, self, new broke)
  for(i <- 1 to 3) {
    val hoe= context.actorOf(Props[Hoe])
  	hoe ! new unemployeed(some(self))
  }
  
  def giveup_hoes(pimp:ActorRef) = {
      pimp ! myhoes(self, hoes.toMap)
      for((hoe, _) <- hoes) {
        hoe ! new move
      }
      hoes.clear()
      retrobution contains pimp unless {
        retrobution += pimp
        context.system.scheduler.scheduleOnce(5.second, self, ("getup", pimp))
      }
  }
  
  def start_a_pimpfight(playa:ActorRef) = {
      playa ! pimpfight(strength)
  }
  
  def receive= {
    case _:jealous => 
      val playa: ActorRef = otha_playas(Random.nextInt(otha_playas.size))
      context.system.scheduler.scheduleOnce(30.second, self, new jealous)
      println(s"$self: $playa has too much, ima take some of it")
      start_a_pimpfight(playa)
    case playa(playa:ActorRef) => 
      otha_playas = otha_playas :+ playa
    case ("getup", playa:ActorRef) => 
      retrobution -= playa
      println(s"$self: pumping iron to whip $playa's booty")
      this.strength = Random.nextInt(100)
      start_a_pimpfight(playa)
    case _:dead => 
      giveup_hoes(sender)
      println(s"$self: its tough on the streets, but ill be back")
    case myhoes(sucka:ActorRef, daHoes: Map[ActorRef, Integer]) =>
      for((hoe, dollars) <- daHoes) {
        val hoe= context.actorOf(Props[Hoe])
        hoe ! new unemployeed(some(self))
        hoes(hoe) = dollars
        println(s"$self: welcome to my street hoe!  remember, bitch betta have my money")
      }
      println(s"$self: we beat dat sucka $sender, took his hoes, im rich bitch")
    case pimpfight(strength) if this.strength == strength => 
      println(s"$self: you best break yo self foo '$sender', im gong to da gym")
      self ! ("getup", sender)
    case pimpfight(strength) if this.strength < strength => 
        giveup_hoes(sender)
    case pimpfight(strength) if this.strength > strength => 
        println(s"$self: eat dat sucka $sender")
        sender ! new dead
    case unemployeed(Some(hoe)) => 
      hoe ! new payment
      hoes += hoe -> 0
    case unemployeed(None) => println("dat hoe took off wit my money")
    case _:broke => 
      hoes transform {(hoe:ActorRef, _) => hoe ! new payment; 0}
      context.system.scheduler.scheduleOnce(5.second, self, new broke)
    case money(dollar) =>
      val current = hoes getOrElseUpdate (sender, 0)
      hoes update (sender, current + dollar)
      dollar match {
        case d if(d < 20) => 
            println(s"$self: man, we have to love on $sender")
            sender ! slap(30)
        case d if (d < 50) =>
            println(s"$self: man, $dollar? we have to rough up $sender")
            sender ! slap(100 - lies.nextInt(50))
        case _ => println(s"$self: man, $sender is a good earner")
      }
  }
}

class Hoe extends Actor {
  val lies = Random
  
  def receive= {
    case _:move =>
        println(s"$self, iz gotta move")
        context.stop(self)
    case unemployeed(Some(tpimp)) => tpimp ! unemployeed(Some(self))
    case slap(force) if force >= 50 => 
        println(s"$self: you aint gotta hitta bitch with $force lbs of force big daddy!")
        sender ! money(100)
    case slap(force) if force < 50 => 
        println(s"$self: sorry big daddy!")
        sender ! money(20)
    case _:payment => 
      println(s"$self: ok big daddy!")
      sender ! money(lies.nextInt(100))
  }
}