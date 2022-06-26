package delivery;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TestKafkaDelivery {

	private static final Logger logger = Logger.getLogger(TestKafkaDelivery.class.getName());
	private final static String testDescription = "\r\n"
			+ "***********************************************"
			+ "  * TestNG case steps: \r\n" 
			+ "  *	1. initiate a kafka producer and a consumer \r\n"
			+ "  *	2. produce 10 messages and store them in a list \r\n"
			+ "  *	3. consume 10 messages and store them in a list \r\n"
			+ "  *	4. compare lists to verify messages are received in the same order they were send \r\n"
			+ "***********************************************/";
	
	static List<String> pList = new ArrayList<String>();
	static List<String> cList = new ArrayList<String>();
	
	@Test
	public static void compareMessagesOrder() {
		Consumer consumer;
		Producer producer;

		try {
			ConfigFile.load();
			logger.info(testDescription);
			consumer = new Consumer(ConfigFile.topic);
			producer = new Producer(ConfigFile.topic);			
			consumer.start();
			producer.start();
			
			while (consumer.isAlive() || producer.isAlive()){
				try {Thread.sleep(500);} catch (InterruptedException e) {}
			}
			
			pList = producer.getMessages();
			cList = consumer.getMessages();

			if(pList.equals(cList)) {
				logger.info("lists are identical");
			}else {
				logger.error("lists differ \r\n" + pList + "\r\n" + cList + "\r\n");
			}

			AssertJUnit.assertEquals(pList.equals(cList), true); 

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			Assert.fail(e.getMessage());

		} finally {
			producer = null;
			consumer = null;
		}
	}
}
