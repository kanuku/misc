package com.example

import scala.concurrent.duration.Duration

import akka.actor._

sealed trait PiMessage
case object Calculate extends PiMessage
case class Work(start: Int, nrOfElements: Int) extends PiMessage
case class Result(value: Double) extends PiMessage
case class PiApproximation(pi: Double, duration: Duration)

object Pi extends App {
//Change to 20000, 10000000 and 10000000 to see the impact on CPU 
  calculate(nrOfWorkers = 20, nrOfElements = 10000000, nrOfMessages = 10000000)

  //actors and messages
  def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) {
    //create an Akka System
    val system = ActorSystem("PiSystem")

    //create the result listener, which will print the result and shutdown the system
    val listener = system.actorOf(Props[Listener], name = "listener")

    //create the master
    val master = system.actorOf(
      Props(new Master(nrOfWorkers, nrOfMessages, nrOfElements, listener)),
      name = "master")

    master ! Calculate
  }
}