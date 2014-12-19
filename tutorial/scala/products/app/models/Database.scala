package models

import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.oneToManyRelation
import org.squeryl.Schema




object Database extends Schema {
  val productsTable = table[Product]("products")
  val stockItemsTable = table[StockItem]("stock_items")
  val warehousesTable = table[Warehouse]("warehouses")
  val productToStockItems = oneToManyRelation(productsTable, stockItemsTable).via((p, s) =>
    p.id === s.productId)
  val warehouseToStockItems = oneToManyRelation(warehousesTable, stockItemsTable).
    via((w, s) => w.id === s.warehouseId)

  on(productsTable) { p =>
    declare {
      p.id is (autoIncremented)
    }
  } 
  
  on(stockItemsTable) { s =>
    declare {
      s.id is (autoIncremented)
    }
  }
  on(warehousesTable) { w =>
    declare {
      w.id is (autoIncremented)
    }
  }
}