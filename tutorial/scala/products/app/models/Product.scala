package models

import java.util.HashMap
import scala.collection.mutable.HashMap
import anorm.SQL
import anorm.SqlQuery
import play.api.Play.current
import play.api.db.DB

case class Product(
  id: Long,
  ean: Long, name: String, description: String)

object Product {

  def findAll = getAll

  def findByEan(ean: Long) = ???

  def add(product: Product) = ???

  val sql: SqlQuery = SQL("select * from products order by name asc")
  def getAll: List[Product] = DB.withConnection {
    implicit connection =>
      sql().map(row =>
        Product(row[Long]("id"), row[Long]("ean"),
          row[String]("name"), row[String]("description"))).toList
  }

  def getAllWithPatterns: List[Product] = DB.withConnection {
    implicit connection =>
      import anorm.Row
      sql().collect {
        case Row(Some(id: Long), Some(ean: Long),
          Some(name: String), Some(description: String)) =>
          Product(id, ean, name, description)
      }.toList
  }

  import anorm.RowParser
  val productParser: RowParser[Product] = {
    import anorm.~
    import anorm.SqlParser._
    long("id") ~
      long("ean") ~
      str("name") ~
      str("description") map {
        case id ~ ean ~ name ~ description =>
          Product(id, ean, name, description)
      }
  }

  import anorm.ResultSetParser
  val productsParser: ResultSetParser[List[Product]] = {
    productParser *
  }

  def getAllWithParser: List[Product] = DB.withConnection {
    implicit connection =>
      sql.as(productsParser)
  }

}