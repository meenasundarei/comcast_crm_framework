package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.utilityclassobject.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.VerifyOrgInfoPage;
import com.crm.generic.baseclassutility.BaseClass;
@Listeners(com.comcast.crm.listenerutility.ListenerImplementation.class)
public class CreateOrg_withBaseclassUsage extends BaseClass {

	@Test
	public void createorgtest() throws Throwable {
		
	
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		// nav to organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to org module");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// click create org button
		UtilityClassObject.getTest().log(Status.INFO, "click org button");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateorgbtn().click();

		// enter details and create org
		UtilityClassObject.getTest().log(Status.INFO, "Enter details and create org");
		OrganizationInformationPage orp = new OrganizationInformationPage(driver);
		orp.enterorg(orgName);

		// validation
		// message verify using assert
		UtilityClassObject.getTest().log(Status.INFO, "Header Verification");
		VerifyOrgInfoPage vop = new VerifyOrgInfoPage(driver);
		String act_header= vop.getHeadermsg().getText();
		boolean status = act_header.contains(orgName);
		Assert.assertTrue(status);
		Reporter.log(act_header);
		
		// verify
		UtilityClassObject.getTest().log(Status.INFO, "Org name verification");
		String act_orgName = vop.getAct_orgname().getText();
		Assert.assertEquals(act_orgName, orgName);
		Reporter.log(act_orgName);

	}
	
	
	@Test
	public void createorg_withindustrytest() throws Throwable
	{
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);
		
		 //nav to organization module
		 driver.findElement(By.linkText("Organizations")).click();
		 //click create org button
		 driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		 
		 //enter details and create org
		 driver.findElement(By.name("accountname")).sendKeys(orgName);
		 WebElement ind = driver.findElement(By.name("industry"));
		 Select sel = new Select(ind);
		 sel.selectByVisibleText(industry);
		 WebElement typ = driver.findElement(By.name("accounttype"));
		 Select sel1= new Select(typ);
		 sel1.selectByVisibleText(type);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		 //validation
		 
		//verify industry
		 String act_industry=driver.findElement(By.id("dtlview_Industry")).getText();
		 if(act_industry.equals(industry))
		 {
			 System.out.println(industry+"is verified-- pass");
		 }
		 else
		 {
			 System.out.println(industry+"is not verified--fail");
			 Assert.fail();
		}
		 //verify type
		 
		 String act_type=driver.findElement(By.id("dtlview_Type")).getText();
		 if(act_type.equals(type))
		 {
			 System.out.println(type+"is verified-- pass");
		 }
		 else
		 {
			 System.out.println(type+"is not verified--fail");
		}
	}
}


