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

import org.dovigo.cli.OptionsType._

/**
 * An option is as the name says an option for commandline executables.
 * 
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
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