package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ActionUtils;
import utils.BaseTest;
import utils.FileUtils;
import utils.waitUtils;

public class TC03_ValidateRememberMe extends BaseTest {

		@Test
		public void validateRememberMe() throws FileNotFoundException, IOException, InterruptedException {
		
		//Launch application and Validate
		lp.launchApplication();
		lp.validatePageTitle();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Login to Application by remembering the creds and validate
		lp.enterUserName(FileUtils.readLoginPropertiesFile("valid.username"));
		lp.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		lp.checkBoxRememberMe();
		Thread.sleep(2000);
		lp.clickLogin();
		//Validate if landed to home page
		String expectedHomePageTitle = FileUtils.readLoginPropertiesFile("homepage.title");	
		waitUtils.waitForTitle(driver, expectedHomePageTitle, 20);
		Assert.assertEquals(driver.getTitle(), expectedHomePageTitle, "After login user should land on home page");
		
		//Click on UserMenu and logout of the application
		hp.clickUserMenu();
		ActionUtils.selectDropDownValue(driver, hp.dropdownElement, "Logout");
		Thread.sleep(2000);
		
		//Validate if the user is saved
		Assert.assertEquals(lp.checkSavedUser(), FileUtils.readLoginPropertiesFile("valid.username"), "Login and saved username should be same");

		System.out.println("TC03_ValidateRememberMe testing is completed");
	}

}
