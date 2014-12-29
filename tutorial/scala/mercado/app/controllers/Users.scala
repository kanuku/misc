package controllers

import play.api.data._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Action
import play.api.mvc.Controller
import models.User

object Users extends Controller {

  def index = Action { 
    implicit request =>{
    Ok(views.html.user.edit(userForm))
    }

  }

  def save = Action {
    NotImplemented
  }

  private val userForm: Form[User] = Form(
    mapping(
      "username" -> nonEmptyText,
      "email" -> email,
      "password" -> nonEmptyText) //
      ((username, email, password) => User(username, email, password, None, None, None)) //
      ((user: User) => Some(user.username, user.email, user.password)))

}