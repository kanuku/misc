package controllers

import play.api.data.Forms.longNumber
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.mvc.Action
import play.api.mvc.Controller
import models.Product
import play.mvc.Http.Request
import play.mvc.Http.RequestHeader

object Products extends Controller with WithCart {

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

  def catalog() = Action {
    implicit request =>
      val products = Product.findAll
      Ok(views.html.products.catalog(products))
  }

}
