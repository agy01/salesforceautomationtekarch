package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;
import utils.FileUtils;
import utils.waitUtils;

public class TC02_UserLoginWithoutError extends BaseTest{

		@Test
		public void validLogin() throws FileNotFoundException, IOException, InterruptedException{		
		
		//Launch Application and Validate
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));	
		String expectedApplicationTitle = FileUtils.readLoginPropertiesFile("prod.application.title");
		Assert.assertEquals(driver.getTitle(), expectedApplicationTitle, "Application should be launched");
		
		//login to Application and Validate if landed on homePage
		lp.loginToApplication(driver, FileUtils.readLoginPropertiesFile("valid.username"), FileUtils.readLoginPropertiesFile("valid.password"));
		String expectedHomePageTitle = FileUtils.readLoginPropertiesFile("homepage.title");
		waitUtils.waitForTitle(driver, expectedHomePageTitle, 20);
		Assert.assertEquals(driver.getTitle(), expectedHomePageTitle, "After login user should land to home page");
		
		System.out.println("TC02_UserLoginWithoutError testing completed");

	}

}
