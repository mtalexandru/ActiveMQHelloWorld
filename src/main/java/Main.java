import org.apache.activemq.broker.BrokerService;

import java.net.URI;

/**
 * Created by malexandru on 3/29/2017.
 */
public class Main {

	public static void main(String[] args) {
		BrokerService brokerService = new BrokerService();
		try {
			brokerService.addConnector(new URI("tcp://localhost:18000"));
			brokerService.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
