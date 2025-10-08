package listenersample;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.baseclassutility.BaseClass;
@Listeners(com.comcast.crm.listenerutility.ListenerImplementation.class)
public class ListenerSample extends BaseClass{
	
	@Test
	public void createinvoicetest()
	{
		String act_title = driver.getTitle();
		Assert.assertEquals(act_title, "login");
		System.out.println("step1");
		System.out.println("step2");
	}
	
	@Test
	public void cct()
	{
		System.out.println("step3");
		System.out.println("step4");
	}

}
