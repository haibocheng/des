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

import org.apache.commons.configuration.Configuration
import org.dovigo.process.Scheduler
import scala.actors.threadpool.BlockingQueue
import java.util.concurrent.ArrayBlockingQueue
import org.dovigo.cli.Command
import org.dovigo.log.Logging

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
  def init(conf:Configuration) = {
    // Process Scheduler
    val scheduler = new Scheduler(conf.getInt("scheduler.max-workers"))
    scheduler.start
    
    scheduler ! new Command(
        conf.getString("commands.command(0).path"))
  }
  
}