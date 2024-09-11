package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;

public class TC06_ValidateUserMenuDropDown extends BaseTest {

		@Test
		public void validateUserMenuDropDown() throws FileNotFoundException, IOException {
		
		//Launch and login to the application
		lp.launchApplication();
		lp.loginToApplication();
		
		//Wait and validate if user is landing on the home page
		Assert.assertTrue(hp.validateHomePageTitle(), "After Login user should land on home page");
		
		//click on user menu drop down
		hp.clickUserMenu();
		
		//Store the expected options of the menu
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present as expected"); 
		
		System.out.println("TC06_ValidateUserMenuDropDown Testing is completed");
		
		}

}
