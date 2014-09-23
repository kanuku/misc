package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) {
        case (block, move) => move match {
          case Left => block.left
          case Right => block.right
          case Up => block.up
          case Down => block.down
        }
      }
  }

  trait Level1 extends SolutionChecker {
    /* terrain for level 1*/

    val level =
      """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }

  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0, 0)), "0,0")
      assert(!terrain(Pos(4, 11)))
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1, 1))
    }
  }

  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }

  test("neighborsWithHistory") {
    new Level1 {
      val expected = Set(
        (Block(Pos(1, 2), Pos(1, 3)), List(Right, Left, Up)),
        (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))).toStream
      val result = neighborsWithHistory(Block(Pos(1, 1), Pos(1, 1)), List(Left, Up))
      assert(result === expected, "is not equal")
    }
  }
  test("newNeighborsOnly") {
    new Level1 {
      val neighbors = Set(
        (Block(Pos(1, 2), Pos(1, 3)), List(Right, Left, Up)),
        (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))).toStream
      val explored = Set(Block(Pos(1, 2), Pos(1, 3)), Block(Pos(1, 1), Pos(1, 1)))

      val expected = Set(
        (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))).toStream

      val result = newNeighborsOnly(neighbors, explored)
      assert(result === expected, "is not equal")
    }
  }

  test("terrainFunction") {
    new Level1 {
      val e = Vector(level.split("\n").map(str => Vector(str: _*)): _*)
      assert((terrainFunction(e)(Pos(0, 0))), "0,0 is in")
      assert((terrainFunction(e)(Pos(2, 0))), "2, 0 is in")
      assert(!(terrainFunction(e)(Pos(3, 0))), "3,0 is out")
      assert((terrainFunction(e)(Pos(0, 1))), "0,1 is in")
      assert((terrainFunction(e)(Pos(5, 1))), "5,1 is in")
      assert(!(terrainFunction(e)(Pos(6, 1))), "6,1 is in")
      assert(!(terrainFunction(e)(Pos(9, 1))), "9,1 is out")
      assert(!(terrainFunction(e)(Pos(0, 3))), "0,3 is out")
      assert(!(terrainFunction(e)(Pos(0, 5))), "0,5 is out")
      assert(!(terrainFunction(e)(Pos(9, 5))), "9,5 is out")
      assert((terrainFunction(e)(Pos(8, 5))), "8,5 is in")
      //S
      assert((terrainFunction(e)(Pos(1, 1))), "1,1 is in")

      //T
      assert((terrainFunction(e)(Pos(7, 4))), "7,4 is in")
    }
  }
  test("findchar") {
    new Level1 {
      val e = Vector(level.split("\n").map(str => Vector(str: _*)): _*)
      assert(findChar('-', e) == Pos(3, 0))
      assert(findChar('S', e) == Pos(1, 1))
      assert(findChar('T', e) == Pos(7, 4))
    }
  }

}
