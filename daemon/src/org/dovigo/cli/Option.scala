package org.dovigo.cli

class Option(var opt:String, var hasArg:Boolean, var value:String = null) {
  
  /**
   * Create the option string. If option expects an argument, the value string
   * is attached with an empty char prefix to the option
   * 
   * @return The option string
   */
  override def toString = {
    "-" + opt + {if (hasArg && value != null) " " + value else ""}
  }
  
}