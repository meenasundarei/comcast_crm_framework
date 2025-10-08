package com.comcast.crm.orgtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrg_WithPhonenumber {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub

		//read data from property file
						FileInputStream fis = new FileInputStream("C:\\Users\\Varunavi\\Desktop\\testdata for DDT\\commondata.properties.txt");
						Properties prop = new Properties();
						prop.load(fis);
						 String browser = prop.getProperty("browser");
						 String url = prop.getProperty("url");
						 String username = prop.getProperty("username");
						 String password = prop.getProperty("password");
						 
						 //generate random number
						 Random random =new Random();
						 int randomint = random.nextInt(200);
						 
						 //read testscriptdata from excel
						 FileInputStream fis1 = new FileInputStream("C:\\Users\\Varunavi\\Desktop\\testdata for DDT\\Book1.xlsx");
						 Workbook wb = WorkbookFactory.create(fis1);
						 Sheet sh = wb.getSheet("org");
						String orgName =  sh.getRow(1).getCell(2).toString()+randomint;
						String phone = sh.getRow(7).getCell(3).toString();
						wb.close();
						
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
						 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
						driver.findElement(By.id("phone")).sendKeys(phone);
						 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						 
						 //validation
						 
						 String act_phone = driver.findElement(By.id("dtlview_Phone")).getText();
						 if(act_phone.equals(phone))
						 {
							 System.out.println(phone+"verified");
						 }
						 else
						 {
							 System.out.println(phone+"not verified");
						 }
						 //logout
						 driver.quit();

			}

		}

