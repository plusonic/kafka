package delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class Consumer extends MessageThread {
	private final ConsumerConnector consumer;
	private final String topic;
	private List<String> lstMessages = new ArrayList<String>();
	private static final Logger logger = Logger.getLogger(TestKafkaDelivery.class.getName());
	
	public Consumer(String topic) {
		logger.info("initializing consumer");
		this.topic = topic;
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
	}

	private static ConsumerConfig createConsumerConfig() {
		Properties props = new Properties();
		props.put("zookeeper.connect", ConfigFile.zkConnect);
		props.put("group.id", ConfigFile.groupId);
		props.put("zookeeper.session.timeout.ms", "400");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");

		return new ConsumerConfig(props);
	}

	public void run() {
		logger.info("consumer thread is running");
		try {
			Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
			topicCountMap.put(topic, 1);
			Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
			KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
			ConsumerIterator<byte[], byte[]> it = stream.iterator();
			String message = new String();
			
			int messageNo = 1;
			while (messageNo<=10) {
				message = new String(it.next().message());
				logger.info("consumer: " + message);
				lstMessages.add(message);
				messageNo++;
			} 
		} catch (Exception ex){
			logger.error(ex);
		}finally {
			consumer.shutdown();
			logger.info("consumer closed");	
		}
	}
	
	public List<String> getMessages() {
		return lstMessages;
	}
}
