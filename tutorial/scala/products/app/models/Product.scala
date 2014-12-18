package models

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Table
import org.squeryl.Query
import collection.Iterable
import org.squeryl.dsl.OneToMany

case class Product(
  id: Long,
  ean: Long,
  name: String,
  description: String) extends KeyedEntity[Long] {
  lazy val stockItems: OneToMany[StockItem] =
    Database.productToStockItems.left(this)
}

object Product {
  import Database.{ productsTable, stockItemsTable }
  def allQ: Query[Product] = from(productsTable) {
    product => select(product) orderBy (product.name desc)
  }

  def findAll: Iterable[Product] = inTransaction {
    allQ.toList
  }

  def productsInWarehouse(warehouse: Warehouse) = {
    join(productsTable, stockItemsTable)((product, stockItem) =>
      where(stockItem.warehouseId === warehouse.id).
        select(product).
        on(stockItem.productId === product.id))
  }

  def productsInWarehouseByName(name: String, warehouse: Warehouse): Query[Product] = {
    from(productsInWarehouse(warehouse)) { product =>
      where(product.name like name).select(product)
    }
  }

  def insert(product: Product): Product = inTransaction {
    productsTable.insert(product)
  }
  def update(product: Product) {
    inTransaction { productsTable.update(product) }
  }

  def addNewProductGood(product: Product, stockItem: StockItem) {
    transaction {
      productsTable.insert(product)
      stockItemsTable.insert(stockItem)
    }
  }

  def getStockItems(product: Product) =
    inTransaction {
      product.stockItems.toList
    }

  def getLargeStockQ(product: Product, quantity: Long) =
    from(product.stockItems)(s =>
      where(s.quantity gt quantity)
        select (s))
}