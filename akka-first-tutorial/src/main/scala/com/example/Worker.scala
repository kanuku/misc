package com.example

import akka.actor.Actor
import akka.actor.actorRef2Scala

class Worker extends Actor {

  def receive = {
    case Work(start, nrOfElements) => sender ! Result(calculatePiFor(start, nrOfElements))
  }

  def calculatePiFor(start: Int, nrOfElements: Int): Double = {
    var acc = 0.0
    for (i <- start until (start + nrOfElements))
      acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
    acc
  }

}
