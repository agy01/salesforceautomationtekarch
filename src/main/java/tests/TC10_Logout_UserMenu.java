package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;


public class TC10_Logout_UserMenu extends BaseTest {

		@Test
		public void validateLogoutOption() throws FileNotFoundException, IOException {
			
			//Launch Application and login
			lp.launchApplication();
			lp.loginToApplication();
			
			//Click on userMenu drop down and validate if all options are present
			hp.clickUserMenu();
			Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present as expected"); 
			
			//Click on logout option from usermenu
			hp.clickLogout();
			
			//Match the page title by waiting for 20 seconds
			Assert.assertTrue(lp.validatePageTitle(), "Login page title should match"); 
			
			System.out.println("TC10_Logout_UserMenu Testing Completed");
		}

}
