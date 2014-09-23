package week1

object week1 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 

  println("Welcome to the Scala worksheet");$skip(70); ;
  val f: PartialFunction[String , String] = { case "ping" => "pong" };System.out.println("""f  : PartialFunction[String,String] = """ + $show(f ));$skip(13); val res$0 = 

  f("ping");System.out.println("""res0: String = """ + $show(res$0));$skip(23); val res$1 = 
  f.isDefinedAt("abc");System.out.println("""res1: Boolean = """ + $show(res$1));$skip(24); val res$2 = 
  f.isDefinedAt("ping");System.out.println("""res2: Boolean = """ + $show(res$2))}

}
