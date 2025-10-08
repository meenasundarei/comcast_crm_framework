package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContact_WithOrg {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		

		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		 JavaUtility jlib = new JavaUtility();
		 WebDriverUtility wlib = new WebDriverUtility();
		 
		//Automate precondition to create organization also
		
		//read data from property file
		 String browser = flib.getDataFromPropertiesFile("browser");
			String url = flib.getDataFromPropertiesFile("url");
			String username = flib.getDataFromPropertiesFile("username");
			String password = flib.getDataFromPropertiesFile("password");
				 
				 //generate random number
				 //read testscriptdata from excel
			
			String orgName = elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
			String contactName = elib.getDataFromExcel("contacts", 7, 3)+jlib.getRandomNumber();
				
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
				 
				 //nav to organization module
				 driver.findElement(By.linkText("Organizations")).click();
				 //click create org button
				 driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				 
				 //enter details and create org
				 driver.findElement(By.name("accountname")).sendKeys(orgName);
				 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				 
				 //validation
			
				//verify
				 String act_orgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
				 if(act_orgName.equals(orgName))
				 {
					 System.out.println(orgName+"is created-- pass");
				 }
				 else
				 {
					 System.out.println(orgName+"is not created--fail");
				}
				 
				 
				 //Nav to contact module
				 //nav to contact module
				 driver.findElement(By.linkText("Contacts")).click();
				 //click create org button
				 driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				 
				 //enter details and create contact
				 driver.findElement(By.name("lastname")).sendKeys(contactName);
				 driver.findElement(By.xpath("//input[@name='account_name']//following-sibling::img[@title='Select']")).click();
				 
				 //switch to child window-->use object for weddriver utility
				 wlib.switchToNewWindowOnURL(driver, "module=Accounts");
				
				
				 driver.findElement(By.id("search_txt")).sendKeys(orgName);
				 driver.findElement(By.name("search")).click();
				 driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
				 
				 //switch back to parent window
				 wlib.switchToNewWindowOnTitle(driver, "Contacts&action");
				
				 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				 
				 //validation
				
				//verify
				 String act_output=driver.findElement(By.id("mouseArea_Organization Name")).getText();
				 if(act_output.trim().equals(orgName))
				 {
					 System.out.println(contactName+"with"+orgName+"is created-- pass");
				 }
				 else
				 {
					 System.out.println(contactName+"with"+orgName+"is not created--fail");
				}
				 
				 //logout
				 driver.quit();

	}

}
