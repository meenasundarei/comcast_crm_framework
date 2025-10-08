package listenersample;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.generic.baseclassutility.BaseClass;

public class RetryListener{
	
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void createinvoicetest()
	{
		//String act_title = driver.getTitle();
		Assert.assertEquals(" ", "login");
		System.out.println("step1");
		System.out.println("step2");
	}

}
