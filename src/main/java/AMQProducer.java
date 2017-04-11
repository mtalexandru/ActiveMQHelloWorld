import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by malexandru on 3/29/2017.
 */
public class AMQProducer {
	public static void main(String[] args) throws JMSException{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:18000");
		TopicConnection topicConnection = connectionFactory.createTopicConnection();
		topicConnection.start();

		TopicSession topicSession = topicConnection.createTopicSession(false,1);
		Topic topic = topicSession.createTopic("Something");
		TopicPublisher publisher = topicSession.createPublisher(topic);
		TextMessage message = topicSession.createTextMessage("message sent test!!!");
		publisher.send(message);
	}
}
