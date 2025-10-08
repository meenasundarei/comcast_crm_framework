package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyOrgInfoPage {
	
	WebDriver driver;
	
	public VerifyOrgInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headermsg;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement act_orgname;

	public WebElement getAct_orgname() {
		return act_orgname;
	}

	public WebElement getHeadermsg() {
		return headermsg;
	}
	
	
	
	
	

}
