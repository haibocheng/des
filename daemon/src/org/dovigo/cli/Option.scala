package org.dovigo.cli
import org.dovigo.cli.OptionsType._

class Option(val opt:String, val hasArg:Boolean, val value:String = null, val optionsType:OptionsType = OptionsType.POSIX ) {
  
  /**
   * Create the option string. If option expects an argument, the value string
   * is attached with an empty char prefix to the option
   * 
   * @return The option string
   */
  override def toString = {
    optionsType + opt + {if (hasArg && value != null) " " + value else ""}
  }
  
}