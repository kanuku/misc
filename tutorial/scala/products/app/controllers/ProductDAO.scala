package controllers

import models.Product
import play.api.data.Forms.longNumber
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.mvc.Action
import play.api.mvc.Controller
import play.mvc.Http.Request

object ProductDAO extends Controller with WithCart {

  def list(pageNumber: Int) = Action {
    NotImplemented
  }

  def list: Seq[Product] = List(
    Product(123, 495, "400 small paperclips", "A box of 400 small paperclips"),
    Product(124, 595, "150 big paperclips", "A box of 150 big paperclips"),
    Product(233, 175, "Blue ballpoint", "Blue ballpoint pen"))

  def details(ean: Long) = Action {
    NotImplemented
  }
  def edit(ean: Long) = Action {
    implicit request => Ok("")
  }
  def update(ean: Long) = Action {
    NotImplemented
  }

  def save = Action {
    NotImplemented
  }

  def catalog() = Action {
    implicit request =>
      val products = ProductDAO.list(0)
      Ok("") //views.html.products.catalog(products))
  }

  def connectToDatabase(implicit host: String) {

  }

  case class Cart(id: String)

  def methodB = {
    connectToDatabase
  }
  def methodC = {
    connectToDatabase
  }
  implicit val hostsasdfasdf = ""

}
