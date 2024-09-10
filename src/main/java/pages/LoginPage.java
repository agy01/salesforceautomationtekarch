package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	// Constructor for initializing the Page Object
	public LoginPage(WebDriver driver) {
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
	
	public void enterUserNameInForgotPasswordPage(String username) {
		this.forgot_username.sendKeys(username);
		this.continueButton.click();
	}
	
	public HomePage loginToApplication(WebDriver driver, String username, String password) {
		this.enterUserName(username);
		this.enterPassword(password);
		this.clickLogin();
		return new HomePage(driver);
	}
}
