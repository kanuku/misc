package quickcheck

import common._
import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import scala.util.Random

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  //Hint 1
  // If you insert any two elements into an empty heap, 
  //finding the minimum of the resulting heap should get 
  //the smallest of the two elements back.
  property("find smallest of Two") = forAll {
    (a: Int, b: Int) =>
      val h = insert(b, insert(a, empty))
      if (b > a)
        findMin(h) == a
      else
        findMin(h) == b
  }

  //hint 2
  //If you insert an element into an empty heap, then 
  //delete the minimum, the resulting heap should be empty.
  property("delete last element") = forAll {
    (a: Int) =>
      isEmpty(deleteMin(insert(a, empty)))
  }

  // hint 3
  //Given any heap, you should get a sorted sequence of elements
  //when continually finding and deleting minima.
  property("findMin1") = forAll { h: H =>
    if (isEmpty(h))
      true
    else {
      def isSortedSequence(h: H): Boolean = {
        val min = findMin(h)
        val restH = deleteMin(h)
        min < findMin(restH) && isSortedSequence(restH)
      }
      isSortedSequence(h)
    }
  }

  //Hint 4
  //Finding a minimum of the melding of any two heaps 
  //should return a minimum of one or the other.
  property("test") = forAll {
    (h1: H, h2: H) =>
      if (isEmpty(h1) || isEmpty(h2))
        true
      else {
        val h = meld(h1, h2)
        val min = findMin(h)
        min == findMin(h1) || min == findMin(h2)
      }
  }

  property("find single min") = forAll {
    (a: Int) =>
      findMin(insert(a, empty)) == a
  }

  lazy val genMap: Gen[Map[Int, Int]] = for {
    k <- arbitrary[Int]
    v <- arbitrary[Int]
    m <- oneOf(value(Map.empty[Int, Int]), genMap)
  } yield m.updated(k, v)

  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[Int]
    h <- oneOf(empty, genHeap)
  } yield insert(a, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
