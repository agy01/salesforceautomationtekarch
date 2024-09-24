package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

import listeners.ListenersSFDC;
import utils.ActionUtils;
import utils.BaseTest;
import utils.FileUtils;
import utils.waitUtils;

@Listeners(ListenersSFDC.class)
public class LoginTest extends BaseTest {

	protected LoginPage lp = null;
//	protected HomePage hp = null;
	
//	@BeforeMethod
	public void loginPage() throws FileNotFoundException, IOException {
		lp = new LoginPage(getDriver());
//		hp = new HomePage(getDriver());
		
	}
	@Test
	public void loginWithoutPassword() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		lp = new LoginPage(getDriver());
		logger.info("Browser instance launched");
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating application Title");
		lp.validatePageTitle(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		lp.enterUserName(FileUtils.readLoginPropertiesFile("valid.username"));
		test.get().info("Entered Username");
		lp.validateUsername(driver);
		test.get().info("Validating the username");
		test.get().info("Password is set clear");
		lp.validatePassword(driver);
		test.get().info("Validating the password");
		System.out.println("I have not been clicked");
		lp.loginButton.click();
		System.out.println("I have been clicked");
		lp.clickLogin();
		test.get().info("Validating the error message");
		Assert.assertEquals(lp.errorMessage(), FileUtils.readLoginPropertiesFile("error.message"),
				"Actual and Expected Error Message should be same");
		System.out.println("TC01_LoginErrorWithoutPassword testing is completed");
		logger.info("TC01_LoginErrorWithoutPassword testing is completed");
	}

	@Test
	public void loginFailureTestCase() throws FileNotFoundException, IOException {
		System.out.println("TC01_LoginErrorWithoutPassword(Failure) testing is completed");
		test.get().info("TestCase created to throw exception");
		throw new ElementClickInterceptedException("Fail test case");
	}

	@Test
	public void validLogin() throws FileNotFoundException, IOException, InterruptedException {
		WebDriver driver = getDriver();
		lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating Application title");
		lp.validatePageTitle(driver);
		test.get().info("Logging in...");
		lp.loginToApplication(driver);
		test.get().info( "Validate home page");
		Assert.assertTrue(hp.validateHomePageTitle(driver), "Homepage should be dispalyed");
		System.out.println("TC02_UserLoginWithoutError testing completed");

	}

//	@Test
	public void validateRememberMe() throws FileNotFoundException, IOException, InterruptedException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating Application title");
		lp.validatePageTitle(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Logging in...");
		lp.enterUserName(FileUtils.readLoginPropertiesFile("valid.username"));
		lp.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		test.get().info("Checkbox Remeber Me...");
		lp.checkBoxRememberMe();
		Thread.sleep(2000);
		test.get().info("Clicking on login...");
		lp.clickLogin();
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
	}

//	@Test
	public void validateForgotPassword() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating Application title");
		lp.validatePageTitle(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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

	}

//	@Test
	public void validateInvalidCreds() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating Application title");
		lp.validatePageTitle(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
	}

}
