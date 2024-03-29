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
package org.dovigo.process

import org.dovigo.log.Logging
import org.dovigo.cli.Command
import scala.actors.Actor
import org.dovigo.cli.Command

/**
 * Scheduler for worker objects
 *
 * @author Hannes Moser
 * @version 0.1
 * @since 0.1
 */
class Scheduler(val maxWorkers: Int) extends Actor with Logging {

	/**
	 * Run scheduler and check for new jobs within the blocking queue
	 */
	def act = {
		loop {
			receive {
				case c: Command => consume(c)
				case _ => info("What the hell i just got here?")
			}
		}
	}

	/**
	 * Consume a job and run it
	 * @param command The command you just consumed to execute immediately
	 */
	protected def consume(command: Command) = {
		val worker = new Worker(command)
		worker.start
	}

}