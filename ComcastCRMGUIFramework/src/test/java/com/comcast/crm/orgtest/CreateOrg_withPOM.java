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
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.VerifyOrgInfoPage;

public class CreateOrg_withPOM {
	
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
				 
		 String orgName = elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
	
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
	 LoginPage lp = new LoginPage(driver);
	 lp.login(username, password);
	 
	 //nav to organization module
	HomePage hp = new HomePage(driver);
	hp.getOrgLink().click();
	
	 //click create org button
	OrganizationPage op = new OrganizationPage(driver);
	op.getCreateorgbtn().click();
	 
	 //enter details and create org
	OrganizationInformationPage orp = new OrganizationInformationPage(driver);
	orp.enterorg(orgName);
	 
	 //validation
	 //message verify
	VerifyOrgInfoPage vop = new VerifyOrgInfoPage(driver);
	String act_orgname= vop.getHeadermsg().getText();
	
	if(act_orgname.contains(orgName))
	{
		System.out.println("verified");
	}
	else
	{
		System.out.println("not verified");
	}
			
	 //logout
	 hp.logout();
	 
	 driver.quit();

}
}
