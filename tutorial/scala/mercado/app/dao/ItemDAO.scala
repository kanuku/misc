package dao
import dao.Database._
import models._
import models.Item
import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode.from
import org.squeryl.PrimitiveTypeMode.inTransaction
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.orderByArg2OrderByExpression
import org.squeryl.PrimitiveTypeMode.select
import org.squeryl.PrimitiveTypeMode.typedExpression2OrderByArg
import org.squeryl.PrimitiveTypeMode.where

object ItemDAO {

  /**
   * Items sorted by id.
   */
  def allQ = from(itemsTable)(item =>
    select(item) orderBy (item.id desc))
  /**
   * Query that finds all Items
   */
  def findAll = inTransaction {
    allQ.toList
  }

  /**
   * Adds a item to the catalog.
   */
  def insert(item: Item) = inTransaction {
    itemsTable.insert(item.copy())
  }

  /**
   * The item with the given id.
   */
  def findById(id: Long) = inTransaction {
    from(itemsTable)(p =>
      where(p.id === id)
        select (p)).headOption
  }

  /**
   * Updates a item in the catalog.
   */
  def update(item: Item) =
    inTransaction {
      itemsTable.update(item)
    }
}
