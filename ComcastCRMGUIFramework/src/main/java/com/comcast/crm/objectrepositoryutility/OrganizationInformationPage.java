package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
WebDriver driver;
	
	public OrganizationInformationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgnameedit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	public WebElement getOrgnameedit() {
		return orgnameedit;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void enterorg(String orgname)
	{
		orgnameedit.sendKeys(orgname);
		savebtn.click();
	}

}
