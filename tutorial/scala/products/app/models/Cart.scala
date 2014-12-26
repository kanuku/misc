package models

case class Cart(products: Map[Int, Product])

object Carts {
  def demoVart() = {
    Cart(Map(
        3 -> ProductDAO.list(0),
        1 -> ProductDAO.list(1))        )
  }
}