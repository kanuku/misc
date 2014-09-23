package week2

object account {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 
  println("Welcome to the Scala worksheet");$skip(28); 
  val acc = new BankAccount;System.out.println("""acc  : week2.BankAccount = """ + $show(acc ));$skip(17); 
  acc deposit 50;$skip(18); val res$0 = 
  acc withdraw 20;System.out.println("""res0: Int = """ + $show(res$0));$skip(18); val res$1 = 
  acc withdraw 20;System.out.println("""res1: Int = """ + $show(res$1));$skip(17); val res$2 = 
  acc withdraw 2;System.out.println("""res2: Int = """ + $show(res$2));$skip(154); 

  def repeat(command: => Unit)(condition: => Boolean): Unit = {
    command
    if (condition) {}
    else {
      repeat(command)(condition)
    }

  };System.out.println("""repeat: (command: => Unit)(condition: => Boolean)Unit""");$skip(72); 

  (1 until 3) foreach (i => "abc" foreach (j => println(i + " " + j)))}
}
