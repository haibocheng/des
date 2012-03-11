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