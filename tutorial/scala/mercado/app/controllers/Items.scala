package controllers

import dao.ItemDAO
import models.Item
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
      Ok(views.html.item.list(items))
  }

  def show(id: Long) = Action { implicit request =>
    ItemDAO.findById(id).map { item =>
      Ok(views.html.item.details(item))
    }.getOrElse(NotFound)
  }

  def edit = Action {
    implicit request =>
      val form = if (request.flash.get("error").isDefined)
        itemForm.bind(request.flash.data)
      else
        itemForm
      Ok(views.html.item.edit(form))

  }

  def save = Action { implicit request =>
    val editForm = itemForm.bindFromRequest()
    editForm.fold(
      hasErrors = { form =>
        Redirect(routes.Items.edit())
      },
      success = { edit =>
        val result=ItemDAO.insert(edit)
        Redirect(routes.Items.list)
      })
  }

  private val itemForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "description" -> optional(text))(Item.apply)(Item.unapply))

} 

