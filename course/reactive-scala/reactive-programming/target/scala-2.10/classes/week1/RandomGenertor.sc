package week1

object RandomGenertor {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

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

  }
  val integers = new Generator[Int] {
    val rand = new java.util.Random
    def generate = rand.nextInt()
  }                                               //> integers  : week1.RandomGenertor.Generator[Int]{val rand: java.util.Random} 
                                                  //| = week1.RandomGenertor$$anonfun$main$1$$anon$3@288315b9
  val booleans = integers map { x => x > 0 }      //> booleans  : week1.RandomGenertor.Generator[Boolean] = week1.RandomGenertor$$
                                                  //| anonfun$main$1$Generator$1$$anon$1@5fd51845

  def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)] {

    def generate = (t.generate, u.generate)
  }                                               //> pairs: [T, U](t: week1.RandomGenertor.Generator[T], u: week1.RandomGenertor.
                                                  //| Generator[U])week1.RandomGenertor.Generator[(T, U)]

  def single[T](x: T): Generator[T] = new Generator[T] {
    def generate = x
  }                                               //> single: [T](x: T)week1.RandomGenertor.Generator[T]

  def choose(lo: Int, hi: Int): Generator[Int] =
    for (x <- integers) yield lo + x % (hi - lo)  //> choose: (lo: Int, hi: Int)week1.RandomGenertor.Generator[Int]

  def oneOf[T](xs: T*): Generator[T] =
    for (idx <- choose(0, xs.length)) yield xs(idx)
                                                  //> oneOf: [T](xs: T*)week1.RandomGenertor.Generator[T]

  def emptyLists = single(Nil)                    //> emptyLists: => week1.RandomGenertor.Generator[scala.collection.immutable.Nil
                                                  //| .type]
  def nonEmptyLists = for {
    head <- integers
    tail <- lists
  } yield head :: tail                            //> nonEmptyLists: => week1.RandomGenertor.Generator[List[Int]]

  def lists: Generator[List[Int]] = for {
    isEmpty <- booleans
    list <- if (isEmpty) emptyLists else nonEmptyLists
  } yield list                                    //> lists: => week1.RandomGenertor.Generator[List[Int]]

  trait Tree {

  }
  case class Inner(Left: Tree, right: Tree) extends Tree
  case class Leaf(x: Int) extends Tree

  def leafs: Generator[Leaf] = for {
    x <- integers

  } yield Leaf(x)                                 //> leafs: => week1.RandomGenertor.Generator[week1.RandomGenertor.Leaf]

  def inners: Generator[Tree] = for {
    l <- trees
    r <- trees
  } yield Inner(l, r)                             //> inners: => week1.RandomGenertor.Generator[week1.RandomGenertor.Tree]

  def trees: Generator[Tree] = for {
    isLeaf <- booleans
    tree <- if (isLeaf) leafs else inners
  } yield tree                                    //> trees: => week1.RandomGenertor.Generator[week1.RandomGenertor.Tree]

  trees.generate                                  //> res0: week1.RandomGenertor.Tree = Inner(Leaf(852733083),Leaf(1494389971))
  def test[T](r: Generator[T], noTimes: Int = 100)(test: T => Boolean) {
    for (_ <- 0 until noTimes) {
      val value = r.generate
      assert(test(value), "Test failed for:" + value)
    }
    println("Test passed", +noTimes + " times")
  }                                               //> test: [T](r: week1.RandomGenertor.Generator[T], noTimes: Int)(test: T => Bo
                                                  //| olean)Unit

  test(pairs(lists, lists)) {
    case (xs, ys) => (xs ++ ys).length >= xs.length
  }                                               //> (Test passed,100 times)

  
}