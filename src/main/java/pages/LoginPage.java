package pages;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.FileUtils;
import utils.waitUtils;

public class LoginPage {
	
	protected WebDriver driver;
	
	// Constructor for initializing the Page Object
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
	public void launchApplication() throws FileNotFoundException, IOException {
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
	}
	
	//Method to validate application page title 
	public boolean validatePageTitle() throws FileNotFoundException, IOException {
		boolean isTitleMatched = false;
		waitUtils.waitForTitle(driver, FileUtils.readLoginPropertiesFile("prod.application.title"), 20);
		String expectedApplicationTitle = FileUtils.readLoginPropertiesFile("prod.application.title");
		String actualApplicationTitle = driver.getTitle();
		if(actualApplicationTitle.equals(expectedApplicationTitle)) {
			isTitleMatched = true;
		}
		return isTitleMatched;
	}
	
	public void enterUserName(String username) {
		this.username.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		this.password.sendKeys(password);
	}
	
	public String errorMessage() {
		return this.error.getText();
	}
	
	public void checkBoxRememberMe() {
		this.rememberMe.click();
	}
	
	public void clickLogin() {
		this.loginButton.click();
	}
	
	public String checkSavedUser() {
		this.savedUser.isDisplayed();
		return this.savedUserIdentity.getText();
	}
	
	public void clickForgotPassword() {
		this.forgotPassword.click();
	}
	
	public void enterUserNameInForgotPasswordPage() throws FileNotFoundException, IOException {
		String username = FileUtils.readLoginPropertiesFile("valid.username");
		this.forgot_username.sendKeys(username);
		this.continueButton.click();
	}
	
	public HomePage loginToApplication() throws FileNotFoundException, IOException {
		String username = FileUtils.readLoginPropertiesFile("valid.username");
		this.enterUserName(username);
		String password = FileUtils.readLoginPropertiesFile("valid.password");
		this.enterPassword(password);
		this.clickLogin();
		return new HomePage(driver);
	}
}
