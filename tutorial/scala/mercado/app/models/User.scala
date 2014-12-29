package models

import java.sql.Timestamp

case class User( //
  username: String, //
  email: String, //
  password: String, //
  created: Option[Timestamp], //
  updated: Option[Timestamp],
  ended: Option[Timestamp]) //
  extends BaseEntity {
  override def toString = "%s - %s " format (username, email)
}
