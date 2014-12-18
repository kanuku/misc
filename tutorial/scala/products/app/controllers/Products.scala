package controllers

import play.api.data.Form
import play.api.data.Forms.{ mapping, longNumber, nonEmptyText }
import play.api.i18n.Messages
import play.api.mvc.{ Action, Controller }
import models.Product
import play.api.mvc.Flash

object Products extends Controller {
  def list(pageNumber: Int) = Action {
    NotImplemented
  }
  def details(ean: Long) = Action {
    NotImplemented
  }
  def edit(ean: Long) = Action {
    NotImplemented
  }
  def update(ean: Long) = Action {
    NotImplemented
  }

  def save = Action {
    NotImplemented
  }

}