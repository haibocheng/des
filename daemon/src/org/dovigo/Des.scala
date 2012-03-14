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
  }

}