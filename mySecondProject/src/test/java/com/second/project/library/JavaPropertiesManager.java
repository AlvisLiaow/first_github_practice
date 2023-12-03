package com.second.project.library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JavaPropertiesManager {
	public static final Logger log = LogManager.getLogger();
	
	private String propertiesFile;
	private Properties prop;
	private FileInputStream input;
	private FileOutputStream output;
	
	public JavaPropertiesManager(String propertiesFilePath) {
		if(propertiesFilePath.length() > 0) {
			propertiesFile = propertiesFilePath;
			prop = new Properties();
		}
	}
	
	public String readProperty(String key) {
		String value = null;
		try {
			input = new FileInputStream(propertiesFile);
			prop.load(input);
			value = prop.getProperty(key);
			log.info("Reading properties file---> " + propertiesFile);
		} catch (Exception e) {
			log.info("Error.." , e);
		}return value;
	}
	
	public void setProperty(String key, String value) {
		try {
			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, null);
			log.info("Properties file is updated/created. ---> " + propertiesFile);
		} catch (Exception e) {
			log.info("Error.." , e);
		}
	}
	
	public void setProperty(String key, String value, String comment) {
		try {
			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, comment);
			log.info("Properties file is updated/created. ---> " + propertiesFile);
		} catch (Exception e) {
			log.info("Error.." , e);
		}
	}

	public static void main(String[] args) {
		
		String filePath2 = "src/test/resources/configuration.properties";
		
		JavaPropertiesManager propertyManager = new JavaPropertiesManager(filePath2);
		propertyManager.readProperty(filePath2);
		
	}
}
