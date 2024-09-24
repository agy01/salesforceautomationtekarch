package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.ListenersSFDC;
import pages.HomePage;
import pages.LoginPage;
import utils.ActionUtils;
import utils.BaseTest;
import utils.FileUtils;
import utils.waitUtils;

@Listeners(ListenersSFDC.class)
public class LoginTest1 extends BaseTest{
		
	@Test
	public void loginWithoutPassword() throws FileNotFoundException, IOException {
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
	public void validLogin() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(driver);
		logger.info("Browser instance launched");
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating application Title");
		lp.loginToApplication(driver);
		test.get().info( "Validate home page");
		HomePage hp = new HomePage(driver);
		Assert.assertTrue(hp.validateHomePageTitle(driver), "Homepage should be dispalyed");
		System.out.println("TC02_UserLoginWithoutError testing completed");
		logger.info("TC01_LoginErrorWithoutPassword testing is completed");
	}

	@Test
	public void validateRememberMe() throws InterruptedException, FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(driver);
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating if Remember me is checked");
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
		test.get().info("Clicking on login...");
		lp.clickLogin();
		HomePage hp = new HomePage(driver);
		test.get().info("Validate home page");
		String expectedHomePageTitle = FileUtils.readLoginPropertiesFile("homepage.title");
		waitUtils.waitForTitle(driver, expectedHomePageTitle, 20);
		Assert.assertEquals(driver.getTitle(), expectedHomePageTitle, "After login user should land on home page");
		test.get().info("Clicking on usermenu drop down...");
		hp.clickUserMenu(driver);
		test.get().info("Clicking on logout..");
		ActionUtils.selectDropDownValue(driver, hp.dropdownElement, "Logout");
		Thread.sleep(2000);
		test.get().info("Checking if username is saved...");
		Assert.assertEquals(lp.checkSavedUser(), FileUtils.readLoginPropertiesFile("valid.username"),
				"Login and saved username should be same");
		System.out.println("TC03_ValidateRememberMe testing is completed");
		logger.info("TC03_ValidateRememberMe testing is completed");

	}
	
	@Test
	public void validateForgetPassword() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(driver);
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Clicking on forget password...");
		lp.clickForgotPassword();
		test.get().info("Validating if forget password page is displayed...");
		Assert.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("forgotPassword.title"),
				"Forgot Password Page should be displayed");
		test.get().info("Entering username...");
		lp.enterUserNameInForgotPasswordPage();
		test.get().info("Validating if email is sent...");
		Assert.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("checkEmail.title"),
				"Password reset email should be sent to the user");
		System.out.println("TC04_ValidateForgotPassword Testing is completed");
		logger.info("TC04_ValidateForgotPassword testing is completed");
	}
	
	@Test
	public void validateInvalidCreds() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(driver);
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Entering invalid username...");
		String expectedUsername = FileUtils.readLoginPropertiesFile("invalid.username");
		lp.enterUserName(expectedUsername);
		String actualUsername = ActionUtils.getElementAttribute(lp.username);
		Assert.assertEquals(actualUsername, expectedUsername, "Actual and Expected Username should be same");
		test.get().info("Entering invalid password...");
		String expectedPassword = FileUtils.readLoginPropertiesFile("invalid.password");
		lp.enterPassword(expectedPassword);
		String actualPassword = ActionUtils.getElementAttribute(lp.password);
		Assert.assertEquals(actualPassword, expectedPassword, "Actual and Expected password should be same");
		test.get().info("Clicking on loginbutton...");
		lp.clickLogin();
		test.get().info("Validating the error...");
		String InvalidCredsErrorMessage = lp.errorMessage();
		Assert.assertEquals(InvalidCredsErrorMessage,
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.",
				"Actual and Expected Error message should be same");
		System.out.println("TC05_ValidateInvalidCredErrorMessage Testing is completed");
		logger.info("TC05_ValidateInvalidCredErrorMessage testing is completed");

	}
	
}
