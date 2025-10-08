package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToNewWindowOnURL(WebDriver driver, String partialURL)
	{
		 Set<String> childwindow = driver.getWindowHandles();
			Iterator<String> it = childwindow.iterator();
			while(it.hasNext())
			{
				String windowId = it.next();
				driver.switchTo().window(windowId);
				
				String act_url = driver.getCurrentUrl();
				if(act_url.contains(partialURL))
				{
					break;
				}
			}
	}
	
	public void switchToNewWindowOnTitle(WebDriver driver, String partialTitle)
	{
		 Set<String> childwindow = driver.getWindowHandles();
			Iterator<String> it = childwindow.iterator();
			while(it.hasNext())
			{
				String windowId = it.next();
				driver.switchTo().window(windowId);
				
				String act_url = driver.getTitle();
				if(act_url.contains(partialTitle))
				{
					break;
				}
			}
	}
	
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String nameID)
	{
		driver.switchTo().frame(nameID);
	}
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebDriver driver, WebElement element, String text)
	{
		Select selobj = new Select(element);
		selobj.selectByVisibleText(text);
	}
	
	public void select(WebDriver driver, WebElement element, int index)
	{
		Select selobj = new Select(element);
		selobj.selectByIndex(index);
	}
	
	public void mouseMoveToElement(WebDriver driver, WebElement element)
	{
		Actions actobj = new Actions(driver);
		actobj.moveToElement(element).perform();
	}
	
	public void doubleclick(WebDriver driver, WebElement element)
	{
		Actions actobj = new Actions(driver);
		actobj.doubleClick(element).perform();
	}
}