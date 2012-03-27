package controllers

import play.api._
import play.api.mvc._
import app.menu.Page
import scala.collection.mutable.MutableList

object Application extends Controller {
  
  implicit val menu = new Page("main",
      routes.Application.index,
      MutableList(
          new Page("Home", routes.Application.index),
          new Page("Projects", routes.Application.projects),
          new Page("Presets", routes.Application.presets),
          new Page("Applications", routes.Application.applications),
          new Page("Docs", routes.Application.docs),
          new Page("Help", routes.Application.help),
          new Page("Administrator",
              routes.Application.administrator,
              MutableList(
                  new Page("Change Settings", routes.Application.administrator),
                  new Page("Logout", routes.Application.administrator)))))
  
  /**
   * Index
   */
  def index = Action { implicit request =>
    Logger.info(menu.prettify())
    Ok(views.html.index())
  }
  
  /**
   * Projects
   */
  def projects = TODO
  
  /**
   * Presets
   */
  def presets = TODO
  
  /**
   * Applications
   */
  def applications = Action { implicit request =>
    Ok(views.html.applications())
  }
  
  /**
   * Docs
   */
  def docs = TODO
  
  /**
   * Help
   */
  def help = TODO
 
  /**
   * Administrator
   */
  def administrator = TODO
  
}