package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContact {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		 JavaUtility jlib = new JavaUtility();
		 WebDriverUtility wlib = new WebDriverUtility();
		

		//REad propertyfile--->use obj for file utility
		
		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");
		
				 //generate random number-->use object for java utility
		  //read testscriptdata from excel--->use object for excel utility
				 
				 String lastname = elib.getDataFromExcel("contacts", 1, 2)+jlib.getRandomNumber();
			
				
				//vtiger selenium code
				
				 WebDriver driver = null;
				 if(browser.equals("chrome"))
				 {
					 driver = new ChromeDriver();
				 }else if(browser.equals("firefox"))
				 {
					 driver = new FirefoxDriver();
				 }else if(browser.equals("edge"))
				 {
					 driver = new EdgeDriver();
				 }
				 else
				 {
					 driver = new ChromeDriver();
				 }
				 
				 //login to app
				 wlib.waitForPageToLoad(driver);
				 driver.get(url);
				 driver.findElement(By.name("user_name")).sendKeys(username);
				 driver.findElement(By.name("user_password")).sendKeys(password);
				 driver.findElement(By.id("submitButton")).click();
				 
				 //nav to contact module
				 driver.findElement(By.linkText("Contacts")).click();
				 //click create org button
				 driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				 
				 //enter details and create contact
				 driver.findElement(By.name("lastname")).sendKeys(lastname);
				 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				 
				 //validation
				 //message verify
				 String header_info=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				 if(header_info.contains(lastname))
				 {
					 System.out.println(lastname+"contact is created-- pass");
				 }
				 else
				 {
					 System.out.println(lastname+"contact is not created--fail");
				 }
				//verify
				 String act_lastname=driver.findElement(By.id("dtlview_Last Name")).getText();
				 if(act_lastname.equals(lastname))
				 {
					 System.out.println(lastname+"is created-- pass");
				 }
				 else
				 {
					 System.out.println(lastname+"is not created--fail");
				}
				 
				 //logout
				 driver.quit();
	}

}
