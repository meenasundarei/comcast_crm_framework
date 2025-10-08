package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class FileUtility {
	
	public String getDataFromPropertiesFile(String key) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties.txt");
		Properties prop = new Properties();
		prop.load(fis);
		String data = prop.getProperty(key);
		return data; 
	}

}
