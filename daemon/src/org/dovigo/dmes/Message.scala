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
package org.dovigo.dmes

import org.dovigo.cli.Options
import scala.collection.mutable.HashMap
import org.dovigo.cli.Command

/**
 * Message
 *
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
class Message(
		var id: String = null,
		var pid: String = null,
		var options: Options = new Options,
		var command: Command = null,
		var extend: Message = null,
		val registry: HashMap[String, Message] = new HashMap) {

	// Extend message
	extending
	
	/**
	 * Initialize message options
	 */
	protected def extending:Unit = {
		// Exit immediately if extend message is null
		if(extend == null)
			return
		
		if(id == null)
			id = extend.id
			
		if(pid == null)
			pid = extend.pid
			
		if(command == null)
			command = extend.command
			
		options.add(extend.options)
	}
	
	/**
	 * Textual representation of this message
	 *
	 * @return Comma concatenated message properties
	 */
	override def toString = {
		"id: " + id + "\n " + "pid: " + pid + "\n" + command + "\n options: " + options + "\n extends: " + extend.toString
	}

}