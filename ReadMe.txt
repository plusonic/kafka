Project written in Java using Eclipse and Maven

prerequisites: 
 - recommended java 1.7.0.2 or later installed (older versions not tested)
 - kafka and zookeeper already installed and running

to execute test from command line, go to ditributable folder:
 1. settings\kafka.properties is the kafka configuration file, adjust accordingly to your preferences
 2. settings\log4j.properties is the configuration file for the logger, adjust accordingly to your preferences
 3. execute: java -cp "*;dependencies\*" org.testng.TestNG settings\testng.xml 
 4. after test is executed, a folder named "test-output" will be created. You can open index.html to see the results