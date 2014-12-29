package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Form
import play.api.data.Forms._

object Authorization extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.auth.login(loginForm))
    
  }

  def authenticate = Action { implicit request =>
    //Ok(views.)
    NotImplemented
  }
  
     private val loginForm = Form(
    tuple(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText))
}