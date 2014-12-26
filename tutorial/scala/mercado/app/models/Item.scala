package models

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode.from
import org.squeryl.PrimitiveTypeMode.inTransaction
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.orderByArg2OrderByExpression
import org.squeryl.PrimitiveTypeMode.select
import org.squeryl.PrimitiveTypeMode.typedExpression2OrderByArg
import org.squeryl.PrimitiveTypeMode.where

case class Item(name: String, description: Option[String]) extends KeyedEntity[Long] {
  override val id = 0L
  override def toString = "%s - %s".format(name, description)
}

object ItemDAO {
  import Database._

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

