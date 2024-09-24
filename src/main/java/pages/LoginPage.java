package pages;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.ActionUtils;
import utils.FileUtils;
import utils.waitUtils;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//PageObject of WebElements

	@FindBy(id="username")
	public WebElement username;
	
	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(id="Login")
	public WebElement loginButton;
	
	@FindBy(id="rememberUn")
	public WebElement rememberMe;
	
	@FindBy(id="forgot_password_link")
	public WebElement forgotPassword;
		@FindBy(id="un")
		public WebElement forgot_username;
		@FindBy(id="continue")
		public WebElement continueButton;
			
	@FindBy(id="error")
	public WebElement error;
	
	@FindBy(id="contentWrapper")
	public WebElement mainPage;
	
	@FindBy(id="hint_back_chooser")
	public WebElement savedUser;
		@FindBy(id="idcard-identity")
		public WebElement savedUserIdentity;
	
	//PageObject of Methods
		
	//Method to launch the application
	public void launchApplication(WebDriver driver) throws FileNotFoundException, IOException {
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		logger.debug("LoginPage : launchApplication:Navigate to login page");
	}
	
	//Method to validate application page title 
	public boolean validatePageTitle(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isTitleMatched = false;
		waitUtils.waitForTitle(driver, FileUtils.readLoginPropertiesFile("prod.application.title"), 20);
		String expectedApplicationTitle = FileUtils.readLoginPropertiesFile("prod.application.title");
		String actualApplicationTitle = driver.getTitle();
		if(actualApplicationTitle.equals(expectedApplicationTitle)) {
			isTitleMatched = true;
		}
		logger.debug("LoginPage : validatePageTitle : Check if the login page title matched");
		return isTitleMatched;
	}
	
	public void enterUserName(String username) {
		this.username.clear();
		this.username.sendKeys(username);
		logger.debug("LoginPage : enterUserName : Username is entered");
	}
	
	public boolean validateUsername(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isUsernameEntered =  false;
		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		String actualUsername = ActionUtils.getElementAttribute(username);
		if(actualUsername.equals(expectedUsername)) {
			isUsernameEntered = true;
		}
		logger.debug("LoginPage : ValidateUsername : Check if the username entered matches");
		return isUsernameEntered;
	}
	
	public void enterPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
		logger.debug("LoginPage : enterPassword : Password is entered");
	}
	
	public boolean validatePassword(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isPasswordEntered =  false;
		String expectedPassword = FileUtils.readLoginPropertiesFile("valid.username");
		String actualPassword = ActionUtils.getElementAttribute(password);
		if(actualPassword.equals(expectedPassword)) {
			isPasswordEntered = true;
		}
		logger.debug("LoginPage : ValidatePassword : Check if the password entered matches");
		return isPasswordEntered;
	}
	
	
	public String errorMessage() {
		logger.debug("LoginPage : errorMessage :Error Message is fetched");
		return this.error.getText();
	}
	
	public void checkBoxRememberMe() {
		this.rememberMe.click();
		logger.debug("LoginPage : checkBoxRememberMe : Remember me is checked");
	}
	
	public void clickLogin() {
		this.loginButton.click();
		logger.debug("LoginPage : clickLogin : Login button is clicked");
	}
	
	public String checkSavedUser() {
		this.savedUser.isDisplayed();
		logger.debug("LoginPage : checkSavedUser : Saved the username");
		return this.savedUserIdentity.getText();
	}
	
	public void clickForgotPassword() {
		this.forgotPassword.click();
		logger.debug("LoginPage : clickForgotPassword : Clicked on forget password");
	}
	
	public void enterUserNameInForgotPasswordPage() throws FileNotFoundException, IOException {
		String username = FileUtils.readLoginPropertiesFile("valid.username");
		this.forgot_username.sendKeys(username);
		this.continueButton.click();
		logger.debug("LoginPage : enterUserNameInForgotPasswordPage : Entered username and clicked continue");
	}
	
	public HomePage loginToApplication(WebDriver driver) throws FileNotFoundException, IOException {
		String username = FileUtils.readLoginPropertiesFile("valid.username");
		this.enterUserName(username);
		String password = FileUtils.readLoginPropertiesFile("valid.password");
		this.enterPassword(password);
		this.clickLogin();
		logger.debug("LoginPage : loginToApplication : Logged out");
		return new HomePage(driver);
	}
}
