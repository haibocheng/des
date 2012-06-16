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

import scala.xml.XML
import org.apache.commons.configuration.Configuration
import org.dovigo.dmes.parser.XmlParser
import org.dovigo.log.Logging
import java.util.Collection
import org.dovigo.cli.CommandRegistry
import org.dovigo.cli.Command

/**
 * Bootstrapping
 *
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
object Bootstrap extends Logging {

	/**
	 * Initialize the application
	 */
	def init(conf: Configuration) = {
		// Register commands
		val commands = conf.getProperty("commands.command.name")
		val size = commands match {
			case c:java.util.Collection[Object] => c.size
			case _ => 0
		}
		
		for(i <- 0 to size) {
			val name = conf.getString("commands.command(" + i + ").name");
			val path = conf.getString("commands.command(" + i + ").path");
			val optionsType = conf.getString("commands.command(" + i + ").options-type");
			val template = conf.getString("commands.command(" + i + ").template");
			
			CommandRegistry.add(
				new Command(name, path))
		}

		// Parse a test message
		val xml = XML.load("demos/messages/full.xml")
		val dmes = XmlParser.create(xml)
		println(dmes)

		// Process Scheduler
		//val scheduler = new Scheduler(conf.getInt("scheduler.max-workers"))
		//scheduler.start

		/*
    	scheduler ! new Command(
        conf.getString("commands.command(0).path"))
		 */
	}

}