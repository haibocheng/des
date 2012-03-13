package org.dovigo.cli
import scala.sys.process.Process
import scala.sys.process.ProcessLogger

class Command(path:String, options:Options) {
  
  /**
   * Run a process and blocks until it is finished. After the process is finnish
   * the stdout and error log alongside the exit value will be returned
   * 
   * @return stdout, error, exit values
   */
  def run = {
    val processBuilder = Process(path + options.createOptionsString)
    var out = List[String]()
    var err = List[String]()
    
    val exit = processBuilder ! ProcessLogger((s) => out ::= s, (s) => err ::= s)
    
    (out.reverse, err.reverse, exit)
  }
  
}