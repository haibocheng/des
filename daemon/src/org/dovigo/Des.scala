package org.dovigo

import org.dovigo.log.Logging
import org.apache.commons.cli.PosixParser
import org.apache.commons.cli.Options
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.HelpFormatter
import org.dovigo.des.queue.Connection
import org.dovigo.cli.Command

/**
 * DES - Dovigo Encoding Server
 * 
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
object Des extends AnyRef with Logging {

  def main(args: Array[String]): Unit = {
    
    // Create CLI parser
    val parser = new PosixParser
    
    // Create options
    val options = new Options
    options.addOption("c", "config", true, "Use a different config then 'config/server-node.xml'")
    options.addOption("h", "help", false, "Print this help")
   
    try {
      // Parse the CLI arguments
      val line = parser.parse(options, args);
      
      if(line.hasOption("h")) {
        val formatter = new HelpFormatter
        return formatter.printHelp("des", options)
      }
    } catch {
      case e : Exception =>
        error(e.getMessage())
    }
    
    // Info
    info("DES - Dovigo Encoding Server")
    
    val option1 = new org.dovigo.cli.Option("option1", true, "val")
    val option2 = new org.dovigo.cli.Option("option2", false)
    val option3 = new org.dovigo.cli.Option("option3", true)
    val option4 = new org.dovigo.cli.Option("option4", false, "val")
    
    val opts = new org.dovigo.cli.Options
    opts.add(option1)
    opts.add(option2)
    opts.add(option3)
    opts.add(option4)
    
    val cmd = new Command("/usr/bin/exec", opts)
    
    println(cmd.full)
  }

}