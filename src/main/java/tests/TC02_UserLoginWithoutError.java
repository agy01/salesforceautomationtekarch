package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;
public class TC02_UserLoginWithoutError extends BaseTest{

		@Test
		public void validLogin() throws FileNotFoundException, IOException, InterruptedException{		
		
		//Launch Application and Validate
		lp.launchApplication();
		lp.validatePageTitle();
		
		//login to Application and Validate if landed on homePage
		lp.loginToApplication();
		Assert.assertTrue(hp.validateHomePageTitle(), "Homepage should be dispalyed");
		
		System.out.println("TC02_UserLoginWithoutError testing completed");

	}

}
