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