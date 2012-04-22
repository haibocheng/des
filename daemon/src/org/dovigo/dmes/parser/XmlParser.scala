/**
 * Copyright (c) 2012 Hannes Moser
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
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
package org.dovigo.dmes.parser

import scala.collection.mutable.HashMap
import scala.xml.Elem
import scala.xml.Node
import org.dovigo.cli.Command
import org.dovigo.cli.Options
import org.dovigo.dmes.Dmes
import org.dovigo.dmes.Message
import org.dovigo.cli.Option
import scala.xml.XML
import java.io.File
import org.dovigo.dmes.NullMessage
import org.dovigo.cli.Command
import org.dovigo.cli.CommandRegistry
import scala.xml.NodeSeq

/**
 * Xml Parser provides methods and constants to load, process XML data to
 * a valid CLI command string, including options with and without values
 *
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
object XmlParser {
	// Constants
	val N_DMES = "dmes"

	val N_MESSAGE = "message"

	val N_FILE = "file"

	val N_CMD = "command"
	val N_OPTIONS = "options"
	val N_OPTION = "option"

	val N_REPORT = "report"

	val A_ID = "@id"
	val A_PID = "@pid"
	val A_EXTENDS = "@extends"
	val A_INPUT = "@input"
	val A_OUTPUT = "@output"
	val A_NAME = "@name"
		
	/**
	 * Commands
	 */
	val commands = new HashMap[String, Command]
		
	/**
	 * Profiles path
	 */
	var profilesPath = "profiles"
		
	/**
	 * List of profiles
	 */
	val profiles = new HashMap[String, Message]
	
	/*
	 * Null Message is used for empty messages
	 */
	val NULL_MESSAGE = new NullMessage

	/**
	 * Set profiles path
	 * 
	 * @param path
	 */
	def setProfilesPath(path:String) = {
		profilesPath = path
	}
	
	/**
	 * Find necessary nodes with path information, options and option values
	 * and create a valid executable command object
	 *
	 * @return The created command object
	 */
	def create(data: Elem): Dmes = {
		val root = data \\ N_DMES
		val path = "test"

		val messages = new HashMap[String, Message]
		for (message <- (root \\ N_MESSAGE)) {
			val m = parseMessage(message, messages)

			messages += (m.id -> m)
		}

		new Dmes(messages)
	}

	/**
	 * Parse a single message element and return created message object
	 *
	 * @return The created message object
	 */
	protected def parseMessage(message: Node, messages: HashMap[String, Message]): Message = {
		// Message options
		val opts = new Options
		
		val options = message \\ N_OPTIONS
		for(option <- (options \\ N_OPTION)) {
			val optionName = option \ A_NAME
			val optionValue = option.text
			val hasOption = !optionValue.isEmpty
			
			opts.add(new Option(optionName.toString, hasOption, optionValue))
		}
		
		// Is this message extending a profile
		val profile = getProfile( (message \ A_EXTENDS).toString )
		
		// Command
		val command = getCommand( (message \\ N_CMD) )
		
		// Create the message object
		new Message(
			(message \ A_ID).toString,
			(message \ A_PID).toString,
			opts,
			command.getOrElse(null),
			profile.getOrElse(NULL_MESSAGE),
			messages)
	}
	
	/**
	 * Get command out of given node
	 * 
	 * @return The command
	 */
	protected def getCommand(node:NodeSeq):scala.Option[Command] = {
		val commandName = node.text
		
		if(commandName.isEmpty)
			return None
		
		if(CommandRegistry.isset(commandName))
			return new Some(CommandRegistry.get(commandName))
		else
			return None
	}
	
	/**
	 * Get a profile by name
	 * 
	 * @return The profile as Message object
	 */
	protected def getProfile(name:String):scala.Option[Message] = {
		if(name.isEmpty)
			return None
			
		if(profiles.contains(name))
			return profiles.get(name)
		
		val profileData = XML.load(profilesPath + File.separator + name + ".xml")
		val p = new Some(parseMessage(profileData, profiles))
		
		profiles += (name -> p.get)
		return p
	}

}