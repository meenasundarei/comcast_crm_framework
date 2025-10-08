package baseclasssample;

import org.testng.annotations.Test;

public class CreateOrgTest_inheritsBaseclassSample extends BaseClassSample {
	
	@Test
	public void createOrg()
	{
		System.out.println("create org and verify");
	}
	
	@Test
	public void createorg_withindustry()
	{
		System.out.println("create org with industry and verify");
	}

}
