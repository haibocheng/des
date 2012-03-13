package org.dovigo.cli

class Option(var opt:String, var hasArg:Boolean, var value:String) {
  
  /**
   * Create the option string. If option expects an argument, the value string
   * is attached with an empty char prefix to the option
   * 
   * @return The option string
   */
  def createOptionString:String = {
    if(hasArg) {
      return opt + " " + value
    }
    return opt
  }
  
}