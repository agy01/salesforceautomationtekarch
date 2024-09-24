package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.ActionUtils;
import utils.BaseTest;
import utils.FileUtils;

public class LoginTest1 extends BaseTest{
	
	@Test
	public void loginErrorTc01() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(driver);
		logger.info("Browser instance launched");
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating application Title");
		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUserName(expectedUsername);
		test.get().info("Entered Username");
		String actualUsername = ActionUtils.getElementAttribute(lp.username);
		Assert.assertEquals(actualUsername, expectedUsername, "Actual and expected useranme should be same");
		lp.password.clear();
		test.get().info("Password is set clear");
		String actualPassword = ActionUtils.getElementAttribute(lp.password);
		Assert.assertEquals(actualPassword, "", "Actual and expected password should be same");
		lp.clickLogin();
		test.get().info("Validating the error message");
		Assert.assertEquals(lp.errorMessage(), FileUtils.readLoginPropertiesFile("error.message"),
				"Actual and Expected Error Message should be same");
		System.out.println("TC01_LoginErrorWithoutPassword testing is completed");
		logger.info("TC01_LoginErrorWithoutPassword testing is completed");
	}
	
	@Test
	public void loginInToSalessforceTC02() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(driver);
		logger.info("Browser instance launched");
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating application Title");
//		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
//		lp.enterUserName(expectedUsername);
//		test.get().info("Entered Username");
//		String actualUsername = ActionUtils.getElementAttribute(lp.username);
//		Assert.assertEquals(actualUsername, expectedUsername, "Actual and expected useranme should be same");
//		lp.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
//		test.get().info("Entered Password");
//		lp.clickLogin();
		lp.loginToApplication(driver);
	}

	@Test
	public void checkRememberMe() throws InterruptedException, FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(driver);
		lp.launchApplication(driver);
		if(lp.rememberMe.isDisplayed()) {
			lp.rememberMe.click();
			System.out.println("Remember me is clicked");
		}else {
			System.out.println("Remember me was not clicked");
		}
		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUserName(expectedUsername);
		String actualUsername = ActionUtils.getElementAttribute(lp.username);
		Assert.assertEquals(actualUsername, expectedUsername, "Actual and expected useranme should be same");
		lp.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		lp.clickLogin();
	}
	
}
