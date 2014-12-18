package models

import org.squeryl.KeyedEntity
import org.squeryl.dsl.ManyToOne



case class StockItem(
  id: Long,
  productId: Long,
  warehouseId: Long,
  quantity: Long) extends KeyedEntity[Long]{
  
  
  lazy val product: ManyToOne[Product] =
    Database.productToStockItems.right(this)
  lazy val warehouse: ManyToOne[Warehouse] =
    Database.warehouseToStockItems.right(this)
}



