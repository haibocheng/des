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
package org.dovigo.des.queue

import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms.Session

class Connection(var dsn:String, var queue:String) {
  
  val factory = new ActiveMQConnectionFactory(dsn)
  val connection = factory.createConnection()
  val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
  var topic = session.createTopic("DesJobs")
  var producer = session.createProducer(topic)
  
  val msg = session.createTextMessage()
  msg.setText("Hello Des Jobs!")
  producer.send(msg)
  
}