package com.example

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.future
import scala.util.Success
import scala.util.Failure

object Hello {

  import scala.concurrent._
  import ExecutionContext.Implicits.global

  //Looks for the String "Duration(length" in file myfile
  val firstOccurrence: Future[Int] = future {
    val source = scala.io.Source.fromURL(getClass.getResource("/myfile.txt"), "utf-8")
    source.toSeq.indexOfSlice("Duration(length")
  }
  //Looks for the String "Analogous" in file myfile
  val firstOccurrence2: Future[Int] = future {
    val source = scala.io.Source.fromURL(getClass.getResource("/myfile.txt"), "utf-8")
    source.toSeq.indexOfSlice("Analogous")
  }

  def main(args: Array[String]): Unit = {
    println("Merging the result of two futures into one single result")
    Future.sequence(List(firstOccurrence, firstOccurrence2)) map {
      resp => println("Result = " + (resp mkString ","))
    }

  }

}
