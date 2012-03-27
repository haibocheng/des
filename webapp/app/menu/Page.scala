package app.menu

import scala.collection.mutable.MutableList
import play.api.mvc.Call

/**
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
class Page(
    val name:String,
    val route:Call,
    val pages:MutableList[Page] = new MutableList()) extends MutableList {
  
  /**
   * Get pretty print of this hierarchical page (ASCII Tree)
   * 
   * @return String 
   */
  def prettify(depth:Int = 0):String = {
    val s = {if (pages.size > 0) "`-- " else "|-- "} +
    		name +
    		"(" + route + ")" +
    		"\n"
    pages.foldLeft(s)((s,o) => s + "    " * (depth + 1) + o.prettify(depth + 1))
  }
  
}