package models

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode.from
import org.squeryl.PrimitiveTypeMode.inTransaction
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.orderByArg2OrderByExpression
import org.squeryl.PrimitiveTypeMode.select
import org.squeryl.PrimitiveTypeMode.typedExpression2OrderByArg
import org.squeryl.PrimitiveTypeMode.where

abstract class BaseEntity extends KeyedEntity[Long] {
  override val id:Long = 0L
  
}


