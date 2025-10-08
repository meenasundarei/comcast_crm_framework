package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

WebDriver driver;
	
	
	public OrganizationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//img[@alt='Create Organization...']")
	private WebElement createorgbtn;
	
	@FindBy(name="search_text")
	private WebElement searchtxt;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchfield;
	
	@FindBy(name="submit")
	private WebElement searchbtn;
	
	
	public WebElement getSearchbtn() {
		return searchbtn;
	}
	public WebElement getCreateorgbtn() {
		return createorgbtn;
	}
	public WebElement getSearchtxt() {
		return searchtxt;
	}

	public WebElement getSearchfield() {
		return searchfield;
	}

}
