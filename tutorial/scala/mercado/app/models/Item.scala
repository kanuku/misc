package models

case class Item(name: String, description: Option[String]) extends BaseEntity {
  override def toString = "%s - %s".format(name, description)
}


