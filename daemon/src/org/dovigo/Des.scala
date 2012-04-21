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
package org.dovigo

import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Options
import org.apache.commons.cli.PosixParser
import org.apache.commons.configuration.XMLConfiguration
import org.apache.commons.configuration.PropertiesConfiguration
import org.dovigo.log.Logging

/**
 * DES - Dovigo Encoding Server
 *
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
object Des extends Logging {

	/**
	 * Main
	 */
	def main(args: Array[String]): Unit = {

		// Create CLI parser
		val parser = new PosixParser

		val conf = new XMLConfiguration
		conf.setValidating(false)
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

		// Bootstrap
		Bootstrap.init(conf)
	}

}