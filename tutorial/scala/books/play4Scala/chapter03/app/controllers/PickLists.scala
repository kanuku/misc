package controllers

import play.api.mvc.Controller
import models.PickList
import play.api._
import play.api.mvc._
import java.util.Date
import scala.concurrent.ExecutionContext

object PickLists extends Controller {
   

  def sendAsync(warehouse: String) = Action {
    import ExecutionContext.Implicits.global
    future {
      val pickList = PickList.find(warehouse)
      send(views.html.pickList(warehouse, pickList, new Date))
    }
    Redirect(routes.PickLists.index())
  }

}