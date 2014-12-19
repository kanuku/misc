package controllers

import play.mvc.Http.RequestHeader

trait WithCart {
  implicit def cart(implicit request: RequestHeader) = {
    // Get cart from session
  }
}