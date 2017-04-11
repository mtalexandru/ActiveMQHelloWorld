package halfbacked;

/**
 * Created by malexandru on 3/29/2017.
 */
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class MessageConsumerTest{

	public static void main(String[] args) throws JMSException{
		ConnectionFactory cf =
				new ActiveMQConnectionFactory("tcp://localhost:18000");
		Destination destination = new ActiveMQQueue("mail.queue");
		Connection conn = null;
		conn = cf.createConnection();
		Session session =
				conn.createSession(false, Session.AUTO_ACKNOWLEDGE);


		MessageConsumer consumer = session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				System.out.println(message);
				MapMessage mapMessage = (MapMessage) message;
				MessageObject mail = new MessageObject();
				try {
					mail.setMailId(mapMessage.getString("mailId"));
					mail.setMessage(mapMessage.getString("message"));
					System.out.println(mail.getMailId() + mail.getMessage());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		conn.start();
	}

}