package delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class Producer extends MessageThread{
	private final kafka.javaapi.producer.Producer<Integer, String> producer;
	private final String topic;
	private final Properties props = new Properties();
	private List<String> lstMessages = new ArrayList<String>();
	private static final Logger logger = Logger.getLogger(TestKafkaDelivery.class.getName());
	
	public Producer(String topic) {
		logger.info("initializing producer");
		this.topic = topic;
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("metadata.broker.list", "localhost:9092");
		producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
	}

	public void run() {
		logger.info("producer thread is running");
		try {
			int messageNo = 1;
			while (messageNo<=10) {
				String message = new String("message_" + messageNo);
				producer.send(new KeyedMessage<Integer, String>(topic, message));
				logger.info("producer: " + message);
				lstMessages.add(message);
				messageNo++;
			}
		}catch (Exception ex) {
			logger.error(ex);
		}finally {
			producer.close();
			logger.info("producer closed");			
		}
	}

	public List<String> getMessages() {
		return lstMessages;
	}
	
}
