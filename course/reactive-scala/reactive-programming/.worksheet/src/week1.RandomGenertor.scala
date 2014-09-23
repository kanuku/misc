package week1

object RandomGenertor {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(82); 
  println("Welcome to the Scala worksheet")

  trait Generator[+T] {

    //Values
    self =>

    def generate: T
    def map[S](f: T => S): Generator[S] = new Generator[S] {
      def generate = f(self.generate)
    }

    def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
      def generate = f(self.generate).generate
    }

  };$skip(424); 
  val integers = new Generator[Int] {
    val rand = new java.util.Random
    def generate = rand.nextInt()
  };System.out.println("""integers  : week1.RandomGenertor.Generator[Int]{val rand: java.util.Random} = """ + $show(integers ));$skip(45); 
  val booleans = integers map { x => x > 0 };System.out.println("""booleans  : week1.RandomGenertor.Generator[Boolean] = """ + $show(booleans ));$skip(128); 

  def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)] {

    def generate = (t.generate, u.generate)
  };System.out.println("""pairs: [T, U](t: week1.RandomGenertor.Generator[T], u: week1.RandomGenertor.Generator[U])week1.RandomGenertor.Generator[(T, U)]""");$skip(83); 

  def single[T](x: T): Generator[T] = new Generator[T] {
    def generate = x
  };System.out.println("""single: [T](x: T)week1.RandomGenertor.Generator[T]""");$skip(99); 

  def choose(lo: Int, hi: Int): Generator[Int] =
    for (x <- integers) yield lo + x % (hi - lo);System.out.println("""choose: (lo: Int, hi: Int)week1.RandomGenertor.Generator[Int]""");$skip(92); 

  def oneOf[T](xs: T*): Generator[T] =
    for (idx <- choose(0, xs.length)) yield xs(idx);System.out.println("""oneOf: [T](xs: T*)week1.RandomGenertor.Generator[T]""");$skip(32); 

  def emptyLists = single(Nil);System.out.println("""emptyLists: => week1.RandomGenertor.Generator[scala.collection.immutable.Nil.type]""");$skip(90); 
  def nonEmptyLists = for {
    head <- integers
    tail <- lists
  } yield head :: tail;System.out.println("""nonEmptyLists: => week1.RandomGenertor.Generator[List[Int]]""");$skip(137); 

  def lists: Generator[List[Int]] = for {
    isEmpty <- booleans
    list <- if (isEmpty) emptyLists else nonEmptyLists
  } yield list

  trait Tree {

  }
  case class Inner(Left: Tree, right: Tree) extends Tree
  case class Leaf(x: Int) extends Tree;System.out.println("""lists: => week1.RandomGenertor.Generator[List[Int]]""");$skip(192); 

  def leafs: Generator[Leaf] = for {
    x <- integers

  } yield Leaf(x);System.out.println("""leafs: => week1.RandomGenertor.Generator[week1.RandomGenertor.Leaf]""");$skip(91); 

  def inners: Generator[Tree] = for {
    l <- trees
    r <- trees
  } yield Inner(l, r);System.out.println("""inners: => week1.RandomGenertor.Generator[week1.RandomGenertor.Tree]""");$skip(118); 

  def trees: Generator[Tree] = for {
    isLeaf <- booleans
    tree <- if (isLeaf) leafs else inners
  } yield tree;System.out.println("""trees: => week1.RandomGenertor.Generator[week1.RandomGenertor.Tree]""");$skip(18); val res$0 = 

  trees.generate;System.out.println("""res0: week1.RandomGenertor.Tree = """ + $show(res$0));$skip(247); 
  def test[T](r: Generator[T], noTimes: Int = 100)(test: T => Boolean) {
    for (_ <- 0 until noTimes) {
      val value = r.generate
      assert(test(value), "Test failed for:" + value)
    }
    println("Test passed", +noTimes + " times")
  };System.out.println("""test: [T](r: week1.RandomGenertor.Generator[T], noTimes: Int)(test: T => Boolean)Unit""");$skip(87); 

  test(pairs(lists, lists)) {
    case (xs, ys) => (xs ++ ys).length >= xs.length
  }}

  
}
