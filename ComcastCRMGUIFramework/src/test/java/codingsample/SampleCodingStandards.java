package codingsample;

import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.crm.generic.baseclassutility.BaseClass;

/**
 * @author Varunavi
 * test class for modulenametest
 */
public class SampleCodingStandards extends BaseClass {

	/**
	 * Scenario: login==>navigate to module==>search==>verify
	 */
	@Test
	public void searchTest()
	{
		/*step1: login to app*/
		LoginPage lp= new LoginPage(driver);
		lp.login("un", "psw");
	}
}
