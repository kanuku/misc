package models
import org.squeryl.{ Session, SessionFactory }
import play.api.db.DB
import play.api.{ Application, GlobalSettings }
import org.h2.engine.SessionFactory
import org.h2.engine.SessionFactory
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.Schema
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl.{ OneToMany, ManyToOne }
import org.squeryl.{ Query, Schema, KeyedEntity, Table }

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    SessionFactory.concreteFactory = Some(() =>
      Session.create(DB.getConnection()(app), new PostgreSqlAdapter))
  }

}

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