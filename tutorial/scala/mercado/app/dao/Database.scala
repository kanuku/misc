package dao

import org.squeryl.Schema
import models.Item
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.oneToManyRelation


object Database extends Schema {
  val itemsTable = table[Item]("items")
  on(itemsTable) { p =>
    declare {
      p.id is (autoIncremented("seq_items_id"))
    }
  }
}
