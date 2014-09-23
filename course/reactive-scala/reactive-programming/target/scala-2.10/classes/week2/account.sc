package week2

object account {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val acc = new BankAccount                       //> acc  : week2.BankAccount = week2.BankAccount@70d04b77
  acc deposit 50
  acc withdraw 20                                 //> res0: Int = 30
  acc withdraw 20                                 //> res1: Int = 10
  acc withdraw 2                                  //> res2: Int = 8

  def repeat(command: => Unit)(condition: => Boolean): Unit = {
    command
    if (condition) {}
    else {
      repeat(command)(condition)
    }

  }                                               //> repeat: (command: => Unit)(condition: => Boolean)Unit

  (1 until 3) foreach (i => "abc" foreach (j => println(i + " " + j)))
                                                  //> 1 a
                                                  //| 1 b
                                                  //| 1 c
                                                  //| 2 a
                                                  //| 2 b
                                                  //| 2 c
}