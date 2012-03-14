package org.dovigo.cli

/**
 * OptionsType enumeration of possible types of options.
 * POSIX and GNU style are supported. 
 */
object OptionsType extends Enumeration {
  type OptionsType = Value
  val POSIX = Value("-")
  val GNU = Value("--")
}