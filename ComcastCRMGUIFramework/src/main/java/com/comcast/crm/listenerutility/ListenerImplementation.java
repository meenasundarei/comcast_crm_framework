package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.utilityclassobject.UtilityClassObject;
import com.crm.generic.baseclassutility.BaseClass;

import sun.util.calendar.BaseCalendar.Date;

public class ListenerImplementation implements ITestListener, ISuiteListener {
	
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		String time = new java.util.Date().toString().replace(" ", "_").replace(":", "_");
		//spark report config
			ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
			spark.config().setDocumentTitle("CRM test results");
			spark.config().setReportName("CRM report");
			spark.config().setTheme(Theme.DARK);
			
			//add system info and create test
			report = new ExtentReports();
			report.attachReporter(spark);
			report.setSystemInfo("OS", "Windows-10");
			report.setSystemInfo("Browser", "Chrome-100");
		}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("---"+result.getMethod().getMethodName()+"---satrt---");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"--start--");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("---"+result.getMethod().getMethodName()+"---end---");
		test.log(Status.PASS, result.getMethod().getMethodName()+"---end---");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		//screenshot program
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		String time = new java.util.Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filepath,testname+" "+time);
		test.log(Status.FAIL, testname+"--Fail--");
		}
}
