package com.crm.generic.baseclassutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.utilityclassobject.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	
	 public WebDriver driver = null;
	 public static WebDriver sdriver = null;
		
	//DatabaseUtility dlib = new DatabaseUtility();
	public FileUtility flib = new FileUtility();
	 public WebDriverUtility wlib = new WebDriverUtility();
	 public ExcelUtility elib = new ExcelUtility();
	 public JavaUtility jlib = new JavaUtility();

	@BeforeSuite
	public void configbs()
	{
		System.out.println("==Connect to  DB, Report configurations==");
		//dlib.getDBConnection(url,username,password);
	}
	
	@BeforeClass
	public void configbc() throws Throwable
	{
		System.out.println("---launch browser---");
		//REad propertyfile--->use obj for file utility
		
		String browser = flib.getDataFromPropertiesFile("browser");
		
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
		 sdriver = driver;
		 UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod
	public void configbm() throws Throwable
	{
		System.out.println("--login--");
		wlib.waitForPageToLoad(driver);
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");
		driver.get(url);
	    LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
	}
	
	@AfterMethod
	public void configam()
	{
		System.out.println("--logout--");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass
	public void configac()
	{
		System.out.println("---close browser---");
		driver.quit();
	}
	
	@AfterSuite
	public void configas()
	{
		System.out.println("==close DB, Report backup==");
		//dlib.closeDBConnection();
	}
	
	
}
