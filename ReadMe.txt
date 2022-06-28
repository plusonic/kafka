prerequisites: 
 - recommended java 1.7.0.2 
 - kafka and zookeeper must already be installed and running

to execute as a test from command line:
 1. download only the distributable folder: https://github.com/plusonic/kafka/tree/main/distributable
 2. settings\kafka.properties is the kafka configuration file, adjust accordingly to your preferences
 3. settings\log4j.properties is the configuration file for the logger
 4. execute: java -cp "*;dependencies\*" org.testng.TestNG settings\testng.xml 
 5. after test is executed, a folder named "test-output" will be created. You can open index.html to see the results
 6. Additionally, KafkaDelivery.log is generated with all detailed steps executed
 
to execute test from eclipse project:
 1. download project: https://github.com/plusonic/kafka
 2. download and install eclipse for java developers
 3. open eclipse, go to marketplace and install "TestNG for Eclipse" and "Eclipse Java Development Tools", if not already installed
 4. go to "file-import-maven-existing maven project" and point to the project's root folder
 5. right click on TestKafkaDelivery.java - run as - TestNG
 

references:
 - https://github.com/sreev/kafka
 - https://cwiki.apache.org/confluence/display/KAFKA/0.8.0+Producer+Example
 - https://kafka.apache.org/quickstart
