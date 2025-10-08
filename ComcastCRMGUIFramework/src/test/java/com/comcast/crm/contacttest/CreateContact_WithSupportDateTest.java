package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
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

public class CreateContact_WithSupportDateTest {

	public static void main(String[] args) throws Throwable {
		
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		 JavaUtility jlib = new JavaUtility();
		 WebDriverUtility wlib = new WebDriverUtility();
		
		//read data from property file
		 String browser = flib.getDataFromPropertiesFile("browser");
			String url = flib.getDataFromPropertiesFile("url");
			String username = flib.getDataFromPropertiesFile("username");
			String password = flib.getDataFromPropertiesFile("password");
		 
		 //generate random number-->create object for java utility
		 //read testscriptdata from excel
			 String lastname = elib.getDataFromExcel("contacts", 4, 2)+jlib.getRandomNumber();
		
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
		 //click create contact button
		 driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		 
		 //enter details and create contact
		 driver.findElement(By.name("lastname")).sendKeys(lastname);
		 
		 //for start date and end date
		 String start_date = jlib.getSystemDate_YYYYMMDD();
		 String end_date = jlib.getRequiredDate_YYYYMMDD(30);
			
		 driver.findElement(By.name("support_start_date")).clear();
		 driver.findElement(By.name("support_start_date")).sendKeys(start_date);
		 
		 driver.findElement(By.name("support_end_date")).clear();
		 driver.findElement(By.name("support_end_date")).sendKeys(end_date);
		 
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
		 
		 //verify date
		 String act_startdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		 if(act_startdate.equals(start_date))
		 {
			 System.out.println(start_date+"is created-- pass");
		 }
		 else
		 {
			 System.out.println(start_date+"is not created--fail");
		}
		 String act_enddate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		 if(act_enddate.equals(end_date))
		 {
			 System.out.println(end_date+"is created-- pass");
		 }
		 else
		 {
			 System.out.println(end_date+"is not created--fail");
		}
		 
		 
		 
		 //logout
		 driver.quit();

	}

}
