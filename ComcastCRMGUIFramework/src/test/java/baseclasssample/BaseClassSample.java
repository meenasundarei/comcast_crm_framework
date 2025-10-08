package baseclasssample;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClassSample {

	@BeforeSuite
	public void configbs()
	{
		System.out.println("==Connect to  DB, Report configurations==");
	}
	
	@BeforeClass
	public void configbc()
	{
		System.out.println("---launch browser---");
	}
	
	@BeforeMethod
	public void configbm()
	{
		System.out.println("--login--");
	}
	
	@AfterMethod
	public void configam()
	{
		System.out.println("--logout--");
	}
	
	@AfterClass
	public void configac()
	{
		System.out.println("---close browser---");
	}
	
	@AfterSuite
	public void configas()
	{
		System.out.println("==close DB, Report backup==");
	}
	
	
}
