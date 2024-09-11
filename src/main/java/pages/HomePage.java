package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ActionUtils;
import utils.FileUtils;
import utils.waitUtils;

public class HomePage {
	
	protected WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@id=\"userNav-arrow\"]")
	public WebElement userMenuDropDown;
		@FindBy(xpath="//div[@id=\"userNavMenu\"]/div[3]/a")
		public List<WebElement> dropdownElement;
			@FindBy(linkText = "My Profile")
			public WebElement myProfile;
			@FindBy(linkText = "My Settings")
			public WebElement mySettings;
			@FindBy(linkText = "Developer Console")
			public WebElement developerConsole;
			@FindBy(linkText = "Switch to Lightning Experience")
			public WebElement switchToLightningExperince;
			@FindBy(linkText = "Logout")
			public WebElement logout;
			
	
	@FindBy(id="home_Tab")
	public WebElement homeTab;
	
	@FindBy(id="Chatter_Tab")
	public WebElement chatterTab;
	
	@FindBy(id="Workspace_Tab")
	public WebElement librariesTab;
	
	@FindBy(id="ContentSearch_Tab")
	public WebElement contentTab;
	
	@FindBy(id="ContentSubscriptions_Tab")
	public WebElement subscriptionsTab;
	
	@FindBy(xpath = "//a[contains(text(), \"Accounts\")]")
	public WebElement accountsTab;
	
	@FindBy(id="Opportunity_Tab")
	public WebElement opportunitiesTab;
	
	@FindBy(id="Contact_Tab")
	public WebElement contactsTab;
	
	@FindBy(id="Lead_Tab")
	public WebElement leadsTab;
	
	@FindBy(xpath="//li/a/img[@title=\"All Tabs\"]")
	public WebElement plusIcon;	
	
	public boolean validateHomePageTitle() throws FileNotFoundException, IOException {
		boolean isHomePageDispalyed = false;
		waitUtils.waitForPageToLoad(driver, 20);
		String expectedTitle = FileUtils.readLoginPropertiesFile("homepage.title");
		if(driver.getTitle().contains(expectedTitle)) {
			return isHomePageDispalyed = true;
		}
		return isHomePageDispalyed;
	}
	
	public void clickUserMenu() {
		waitUtils.waitForElementToBeClickable(driver, this.userMenuDropDown, 15);
		this.userMenuDropDown.click();
	}
	
	public boolean areAllOptionsPresent() throws FileNotFoundException, IOException {
		String options = FileUtils.readLoginPropertiesFile("usermenu.options");
		List<String> expectedOptions = Arrays.asList(options.split(","));
		expectedOptions.replaceAll(String::trim);
		boolean actualOptions = ActionUtils.validateDropDownOptions(this.dropdownElement, expectedOptions);
		Assert.assertTrue(actualOptions, "All options should be present in the drop down");
		return actualOptions;
	}
	
	public MyProfilePage goToMyProfile() {
		this.myProfile.click();
		return new MyProfilePage(driver);
	}
	
	public boolean validateMyProfilePage() throws FileNotFoundException, IOException {
		boolean isMyProfilePageOpened = false;
		String expectedTitle = FileUtils.readMyProfilePropertiesFile("myprofile.title");
		if(driver.getTitle().contains(expectedTitle)) {
			isMyProfilePageOpened = true;
		}
		return isMyProfilePageOpened;
	}
	
	public MySettingsPage goToMySettings() {
		this.mySettings.click();
		return new MySettingsPage(driver);
	}
	
	public boolean validateMySettingsPage() throws FileNotFoundException, IOException {
		boolean isMySettingsPageOpened = false;
		String expectedTitle = FileUtils.readMySettingsPropertiesFile("mysettings.title");
		if(driver.getTitle().contains(expectedTitle)) {
			isMySettingsPageOpened = true;
		}
		return isMySettingsPageOpened;
	}
	
	public DeveloperConsole goToDeveloperConsole() {
		this.developerConsole.click();
		return new DeveloperConsole(driver);
	}
	
	public LoginPage clickLogout() {
		this.logout.click();
		return new LoginPage(driver);
	}
	
}
