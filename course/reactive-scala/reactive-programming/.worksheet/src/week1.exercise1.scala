package week1

object exercise1 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(77); 
  println("Welcome to the Scala worksheet");$skip(113); 
 val f: PartialFunction[List[Int], String] = {
							case Nil => ”one”
							case x :: y :: rest => ”two”
			};System.out.println("""f  : PartialFunction[List[Int],String] = """ + $show(f ))}
			
			
			
			
			
			
}
