package controllers

import play.api.mvc.Action
import play.api.mvc.Controller


class Shop extends Controller with WithCart {

  def catalog() = Action { implicit request =>
    val products = ProductDAO.list
    Ok(views.html.products.catalog(products))
  }

}