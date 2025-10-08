package ExtentReportsSample;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.listenerutility.ListenerImplementation;
import com.crm.generic.baseclassutility.BaseClass;

public class SampleExtentReportTest extends ListenerImplementation {

	@Test
	public void tc1()
	{
		
	ExtentTest test = report.createTest("tc1");
	
	//print
	test.log(Status.INFO, "login to app");
	test.log(Status.INFO, "navigate to contact page");
	test.log(Status.INFO, "Create contact");
	if("HDFC".equals("HDFC"))
	{
	test.log(Status.PASS, "contact created");
	}
	else
	{
		test.log(Status.FAIL, "contact not created");
	}
	}
	
	@Test
	public void tc2()
	{
		
	ExtentTest test = report.createTest("tc2");
	
	//print
	test.log(Status.INFO, "login to app");
	test.log(Status.INFO, "navigate to contact page");
	test.log(Status.INFO, "Create contact");
	if("HDFC".equals("HDFC"))
	{
	test.log(Status.PASS, "contact created");
	}
	else
	{
		test.log(Status.FAIL, "contact not created");
	}
	}
	@Test
	public void tc3()
	{
		
	ExtentTest test = report.createTest("tc3");
	
	//print
	test.log(Status.INFO, "login to app");
	test.log(Status.INFO, "navigate to contact page");
	test.log(Status.INFO, "Create contact");
	if("HDFC".equals("HDFC"))
	{
	test.log(Status.PASS, "contact created");
	}
	else
	{
		test.log(Status.FAIL, "contact not created");
	}
	}
	
	}



