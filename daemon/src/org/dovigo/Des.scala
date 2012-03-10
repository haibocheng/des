package org.dovigo

import org.dovigo.log.Logging
import org.apache.commons.cli.PosixParser
import org.apache.commons.cli.Options
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.HelpFormatter

/**
 * DES - Dovigo Encoding Server
 * 
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
object Des extends AnyRef with Logging {

  def main(args: Array[String]): Unit = {
    
    info("DES - Dovigo Encoding Server")
    
    // Create CLI parser
    val parser = new PosixParser
    
    // Create options
    val options = new Options()
    options.addOption("c", "config", true, "Provide a config.xml file location, otherwise config/server-node.xml will be used.")
    options.addOption("h", "help", false, "Prints this help.")
   
    try {
      // Parse the cli arguments
      val line = parser.parse(options, args);
      
      if(line.hasOption("h")) {
        val formatter = new HelpFormatter
        return formatter.printHelp("des", options)
      }
    } catch {
      case e : Exception =>
        error(e.getMessage())
    }
  }

}