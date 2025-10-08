package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class DeleteOrg {

public static void main(String[] args) throws Throwable {
		
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
					 
			 String orgName = elib.getDataFromExcel("org", 10, 2)+jlib.getRandomNumber();
		
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
		 //message verify
		 String header_info=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		 if(header_info.contains(orgName))
		 {
			 System.out.println(orgName+"is created-- pass");
		 }
		 else
		 {
			 System.out.println(orgName+"is not created--fail");
		 }
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
		 
		 //go back to org page
		 driver.findElement(By.linkText("Organizations")).click();
		 
		 //search for orgName created
		 OrganizationPage op = new OrganizationPage(driver);
		 op.getSearchtxt().sendKeys(orgName);
		 wlib.select(driver, op.getSearchfield(), "Organization Name");
		 op.getSearchbtn().click();
		 
		 //identify delete link of the orgName
		 driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		 
		 
		 //logout
		 driver.quit();
}
}
