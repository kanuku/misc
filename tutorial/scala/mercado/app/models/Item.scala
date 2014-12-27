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


