package dao
import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode.from
import org.squeryl.PrimitiveTypeMode.inTransaction
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.orderByArg2OrderByExpression
import org.squeryl.PrimitiveTypeMode.select
import org.squeryl.PrimitiveTypeMode.typedExpression2OrderByArg
import org.squeryl.PrimitiveTypeMode.where
import models.Database._
import models._
object ItemDAO {

  /**
   * Products sorted by id.
   */
  def allQ = from(itemsTable)(product =>
    select(product) orderBy (product.id desc))
  /**
   * Query that finds all products
   */
  def findAll = inTransaction {
    allQ.toList
  }

  /**
   * Adds a product to the catalog.
   */
  def insert(product: Item) = inTransaction {
    itemsTable.insert(product.copy())
  }

  /**
   * The product with the given id.
   */
  def findById(id: Long) = inTransaction {
    from(itemsTable)(p =>
      where(p.id === id)
        select (p)).headOption
  }

  /**
   * Updates a product in the catalog.
   */
  def update(product: Item) =
    inTransaction {
      itemsTable.update(product)
    }
}
