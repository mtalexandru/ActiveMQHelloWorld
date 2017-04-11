import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by malexandru on 3/29/2017.
 */
public class AMQClient {
	public static void main(String[] args) throws JMSException{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:18000");
		TopicConnection topicConnection = connectionFactory.createTopicConnection();
		topicConnection.start();
		TopicSession topicSession = topicConnection.createTopicSession(false,1);
		Topic topic = topicSession.createTopic("Something");
		TopicSubscriber subscriber = topicSession.createSubscriber(topic);
		subscriber.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				try {
					System.out.println(((TextMessage) message).getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
