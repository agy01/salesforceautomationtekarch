package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.math3.analysis.function.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.HomePage;
import utils.BaseTest;
import utils.FileUtils;
import utils.waitUtils;

public class TC10_Logout_UserMenu extends BaseTest {

		@Test
		public void validateLogoutOption() throws FileNotFoundException, IOException {
			
			//Launch Application and login
			driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
			lp.loginToApplication(driver, FileUtils.readLoginPropertiesFile("valid.username"), FileUtils.readLoginPropertiesFile("valid.password"));
			
			//Wait for the usermenu drop down to be clickable before clicking and validate if all options are present
			waitUtils.waitForElementToBeClickable(driver, hp.userMenuDropDown, 20);
			hp.clickUserMenu();
			hp.areAllOptionsPresent();
			
			//Click on logout option from usermenu
			hp.clickLogout();
			
			//Match the page title by waiting for 20 seconds
			String expectedHomePageTitle = FileUtils.readLoginPropertiesFile("prod.application.title");
			waitUtils.waitForTitle(driver, expectedHomePageTitle, 20);
			Assert.assertEquals(driver.getTitle(), expectedHomePageTitle, "Actual and expected Title should be same after logout");
			
			System.out.println("TC10_Logout_UserMenu Testing Completed");
		}

}
