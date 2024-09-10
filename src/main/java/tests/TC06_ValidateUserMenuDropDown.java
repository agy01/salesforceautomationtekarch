package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;
import utils.FileUtils;
import utils.waitUtils;

public class TC06_ValidateUserMenuDropDown extends BaseTest {

		@Test
		public void validateUserMenuDropDown() throws FileNotFoundException, IOException {
		
		//Launch the application
		lp.launchApplication();
		
		//Login to the application
		lp.loginToApplication(driver, FileUtils.readLoginPropertiesFile("valid.username"), FileUtils.readLoginPropertiesFile("valid.password"));
		
		//Wait and validate if user is landing on the home page
		waitUtils.waitForTitle(driver, FileUtils.readLoginPropertiesFile("homepage.title"), 20);
		Assert.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("homepage.title"), "After Login user should land on home page");
		
		//Wait for the dropdown element to be visibile and perform click function
		waitUtils.waitForElementToBeVisible(driver, hp.userMenuDropDown, 20);
		hp.clickUserMenu();
		
		//Store the expected options of the menu
		hp.areAllOptionsPresent();
		
		System.out.println("TC06_ValidateUserMenuDropDown Testing is completed");
		
		}

}
