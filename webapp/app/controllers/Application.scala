package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  /**
   * Index
   */
  def index = Action {
    Ok(views.html.index())
  }
  
  /**
   * Applications
   */
  def applications = Action {
    Ok(views.html.applications())
  }
  
}