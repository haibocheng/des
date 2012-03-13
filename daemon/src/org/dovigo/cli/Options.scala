package org.dovigo.cli
import scala.collection.mutable.ListBuffer

class Options {

  val options = ListBuffer[Option]()
  
  /**
   * Creates and add a single option to this options object. After adding to this
   * object, the newly created Option object will be returned
   * 
   * @param opt The single-character option name
   * @param hasArg If this option expects an argument
   * @param value The argument
   * @return The created option
   */
  def addOption(opt:String, hasArg:Boolean, value:String):Option = {
    options += new Option(opt, hasArg, value)
    options.last
  }
  
  /**
   * Creates the CLI option string from the added options and arguments and
   * afterwards returns the string
   * 
   * @return The created options string
   */
  def createOptionsString:String = {
    var s = ""
    for(option <- options) s + " " + option.createOptionString
    return s
  }
  
}