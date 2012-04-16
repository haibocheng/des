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

import scala.sys.process.Process
import scala.sys.process.ProcessLogger

class Command(path:String, options:Options, arguments:List[String] = List[String]()) {
  
  /**
   * Run a process and blocks until it is finished. After the process is finish
   * the stdout and error log alongside the exit value will be returned
   * 
   * @return stdout, error, exit values
   */
  def run = {
    val processBuilder = Process(full)
    var out = List[String]()
    var err = List[String]()
    
    val exit = processBuilder ! ProcessLogger((s) => out ::= s, (s) => err ::= s)
    
    (out.reverse, err.reverse, exit)
  }
  
  
  /**
   * Create and return the full command string including the path to the
   * executable with all options(+arguments) set.
   * 
   * @return The full execution string
   */
  def full = {
    path.trim + options + (if(arguments.length > 0) " " + arguments.mkString(" ") else "")
  }
  
}