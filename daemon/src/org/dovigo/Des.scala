package org.dovigo

import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Options
import org.apache.commons.cli.PosixParser
import org.apache.commons.configuration.XMLConfiguration
import org.dovigo.log.Logging
import org.apache.commons.configuration.PropertiesConfiguration

/**
 * DES - Dovigo Encoding Server
 *
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
object Des extends AnyRef with Logging {

  /**
   * Main
   */
  def main(args: Array[String]): Unit = {

    // Create CLI parser
    val parser = new PosixParser
    
    val conf = new XMLConfiguration
    conf.setValidating(true)
    conf.setFileName("conf/server-node.xml")

    // Create options
    val options = new Options
    options.addOption("c", "config", true, "Use a different config then 'config/server-node.xml'")
    options.addOption("h", "help", false, "Print this help")

    try {
      // Parse the CLI arguments
      val line = parser.parse(options, args);

      if (line.hasOption("h")) {
        val formatter = new HelpFormatter
        return formatter.printHelp("des", options)
      }

      if (line.hasOption("c")) {
        conf.setFileName(line.getOptionValue("c"))
      }
    } catch {
      case e: Exception =>
        error(e.getMessage())
    }
    
    // Info
    info("DES - Dovigo Encoding Server")

    // Load config
    conf.load()

  }

}