package models

import anorm.RowParser

case class StockItem(id: Long, product_id: Long, warehouse_id: Long, quantity: Long)

object StockItems {

  val stockItemParser: RowParser[StockItem] = {
    import anorm.SqlParser._
    import anorm.~
    long("id") ~ long("product_id") ~
      long("warehouse_id") ~ long("quantity") map {
        case id ~ productId ~ warehouseId ~ quantity =>
          StockItem(id,  productId, warehouseId, quantity)
      }
  }

}