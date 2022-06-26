package delivery;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/*
 *  class reads the configuration files and returns values from it
 */
public class ConfigFile {

	private static Properties file = new Properties();
	private static String seperator = "";
	private final static String fileName = "kafka.properties";

	public static String zkConnect = "";
	public static String groupId  = "";
	public static String topic = "";
	public static String topic2 = "";
	public static String topic3 = "";	
	public static String kafkaServerURL = "";
	public static int kafkaServerPort = 0;
	public static int kafkaProducerBufferSize = 64 * 1024;
	public static int connectionTimeOut = 0;
	public static int reconnectInterval = 0;
	public static String clientId = "";
	
	// the file object is our properties file handler
	public static Properties getFile() throws Exception {
		if (file == null)
			load();
		return file;
	}

	// initiate our file object
	public static void load() throws Exception {

		FileInputStream in = null;

		System.out.println("loading project properties file");
		String configFilePath = System.getProperty("user.dir");

		// support for windows/Linux
		if (configFilePath.contains("\\")) {
			seperator = "\\";
		} else if (configFilePath.contains("/")) {
			seperator = "/";
		}

		configFilePath = configFilePath + seperator + "settings" + seperator + fileName;

		System.out.println("path to file: " + configFilePath);
		try {
			in = new FileInputStream(configFilePath.trim());
			file.load(in);
			in.close();
			
			PropertyConfigurator.configure(file.getProperty("log4j"));
			
			ConfigFile.zkConnect = file.getProperty("zkConnect").trim();
			ConfigFile.groupId = file.getProperty("groupId").trim();
			ConfigFile.topic = file.getProperty("topic").trim();
			ConfigFile.kafkaServerURL = file.getProperty("kafkaServerURL").trim();
			ConfigFile.kafkaServerPort = Integer.parseInt(file.getProperty("kafkaServerPort").trim());
			ConfigFile.kafkaProducerBufferSize = Integer.parseInt(file.getProperty("kafkaProducerBufferSize").trim());
			ConfigFile.connectionTimeOut = Integer.parseInt(file.getProperty("connectionTimeOut").trim());
			ConfigFile.reconnectInterval = Integer.parseInt(file.getProperty("reconnectInterval").trim());
			ConfigFile.clientId = file.getProperty("clientId").trim();
			
		} catch (Exception e) {
			System.out.println("properties file loading failed, ending..");
			e.printStackTrace();
			throw new Exception(e);
		}

	}

	public static String getSeperator() {
		return seperator;
	}
}
