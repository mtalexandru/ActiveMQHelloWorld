package halfbacked;

/**
 * Created by malexandru on 3/29/2017.
 */
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class MessageProducerTest{
	public static void main(String[] args) throws JMSException{
		MessageObject messageObject = new MessageObject("cucu","bibi");
		sendMessage(messageObject);
	}
	public static void sendMessage(MessageObject mail) {
//TODO	Step 1: Create Connection Factory
		ConnectionFactory cf =
				new ActiveMQConnectionFactory("tcp://localhost:18000");
//TODO	Step 2: Define Destination
		Destination destination = new ActiveMQQueue("mail.queue");
		Connection conn = null;
		try{
//TODO		 Step 3: Create connection from connection factory
			conn = cf.createConnection();
//TODO		 Step 4: Create session
			Session session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
//TODO		 Step 5: Create Message Producer for destination queue
			MessageProducer producer = session.createProducer(destination);
//TODO		 Step 6: Create Message
			MapMessage message = session.createMapMessage();
			message.setString("mailId", mail.getMailId());
			message.setString("message",mail.getMessage());
//TODO		 Step 7: Send message
			producer.send(message);
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}