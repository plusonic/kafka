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
			+ "**************************************************************************************\r\n"
			+ "  * Test case steps: \r\n" 
			+ "  *	1. initiate a producer and a consumer thread \r\n"
			+ "  *	2. produce 10 messages and store them in a list \r\n"
			+ "  *	3. consume 10 messages and store them in a list \r\n"
			+ "  *	4. compare lists to verify messages are received in the same order they were send \r\n"
			+ "**************************************************************************************/";
	
	static List<String> pList = new ArrayList<String>();
	static List<String> cList = new ArrayList<String>();
	
	@Test
	public static void compareMessagesOrder() {
		Consumer consumer;
		Producer producer;

		try {
			ConfigFile.load(); //load configuration from kafka.properties
			logger.info(testDescription);
			consumer = new Consumer(ConfigFile.topic);
			producer = new Producer(ConfigFile.topic);			
			//start threads
			consumer.start();
			producer.start();
			
			int timeout = 0;
			//wait threads to complete
			while (consumer.isAlive() || producer.isAlive()){
				try {Thread.sleep(500);} catch (InterruptedException e) {}
				if(timeout++>60) break; //avoid endless loop
			}
			
			//retrieve each thread's messages
			pList = producer.getMessages();
			cList = consumer.getMessages();

			//use java array lists build in method to compare the messages
			boolean isEqualLists = pList.equals(cList);
			if(isEqualLists) {
				logger.info("\r\n**************************\r\n TEST PASSED lists are identical \r\n" + pList + "\r\n" + cList + "\r\n*************************\r\n");
			}else {
				logger.error("\r\n*************************\r\n TEST FAILED lists differ \r\n" + pList + "\r\n" + cList + "\r\n*************************\r\n");
			}

			//report testNG result
			AssertJUnit.assertEquals(isEqualLists, true); 

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
