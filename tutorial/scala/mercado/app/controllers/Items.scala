package controllers

import models.Item
import models.ItemDAO
import play.api.data._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.Forms.longNumber
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.mvc.Action
import play.api.mvc.Controller

object Items extends Controller {

  def list = Action {
    implicit request =>
      val items = ItemDAO.findAll
      Ok(views.html.items.list(items))
  }

  def show(id: Long) = Action { implicit request =>
    ItemDAO.findById(id).map { item =>
      Ok(views.html.items.details(item))
    }.getOrElse(NotFound)
  }

  def newItem = Action {
    implicit request =>
      val form = if (request.flash.get("error").isDefined)
        itemForm.bind(request.flash.data)
      else
        itemForm
      Ok(views.html.items.edit(form))

  }

  def save = Action { implicit request =>
    val newItemForm = itemForm.bindFromRequest()
    newItemForm.fold(
      hasErrors = { form =>
        Redirect(routes.Items.newItem())
      },
      success = { newItem =>
        val result=ItemDAO.insert(newItem)
        Redirect(routes.Items.show(result.id))
      })
  }

  private val itemForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "description" -> optional(text))(Item.apply)(Item.unapply))

} 

