/**
 * Copyright (c) 2012 Hannes Moser
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * ofthis software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.dovigo.cli

import scala.collection.mutable.ListBuffer

/**
 * Options can contain several option objects. The options class is able to
 * render a proper CLI options string.
 * 
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
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
  def add(opt:String, hasArg:Boolean, value:String = ""):Option = {
    options += new Option(opt.trim, hasArg, value.trim)
    options.last
  }
  
  /**
   * @see add
   */
  def add(opt:Option):Option = {
    options += opt
    options.last
  }
  
  /**
   * Creates the CLI option string from the added options and arguments and
   * afterwards returns the string
   * 
   * @return The created options string
   */
  override def toString = {
    options.foldLeft("")((s,o) => s + " " + o)
  }
  
}