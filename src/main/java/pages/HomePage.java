package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utils.ActionUtils;
import utils.FileUtils;
import utils.waitUtils;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
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
	
	@FindBy(xpath = "//li[@id= \"Account_Tab\"]")
	public WebElement accountsTab;
	
	@FindBy(id="Opportunity_Tab")
	public WebElement opportunitiesTab;
	
	@FindBy(id="Contact_Tab")
	public WebElement contactsTab;
	
	@FindBy(id="Lead_Tab")
	public WebElement leadsTab;
	
	@FindBy(xpath="//li/a/img[@title=\"All Tabs\"]")
	public WebElement plusIcon;	
	
	
	//Method will validate the Home page title
	public boolean validateHomePageTitle(WebDriver driver
			) throws FileNotFoundException, IOException {
		boolean isHomePageDispalyed = false;
		waitUtils.ExplicityWait(driver, this.userMenuDropDown);
		String expectedTitle = FileUtils.readLoginPropertiesFile("homepage.title");
		if(driver.getTitle().contains(expectedTitle)) {
			return isHomePageDispalyed = true;
		}
		return isHomePageDispalyed;
	}
	
	
	public void clickUserMenu(WebDriver driver) {
		waitUtils.waitForElementToBeClickable(driver, this.userMenuDropDown, 15);
		this.userMenuDropDown.click();
	}
	
	//Method validates if all options under the user menu is present as expected
	public boolean areAllOptionsPresent() throws FileNotFoundException, IOException {
		String options = FileUtils.readLoginPropertiesFile("usermenu.options");
		List<String> expectedOptions = Arrays.asList(options.split(","));
		expectedOptions.replaceAll(String::trim);
		boolean actualOptions = ActionUtils.validateDropDownOptions(this.dropdownElement, expectedOptions);
		Assert.assertTrue(actualOptions, "All options should be present in the drop down");
		return actualOptions;
	}
	
	public MyProfilePage goToMyProfile(WebDriver driver) {
		this.myProfile.click();
		return new MyProfilePage(driver);
	}
	
	//Method will validate the home page Title
	public boolean validateMyProfilePage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isMyProfilePageOpened = false;
		String expectedTitle = FileUtils.readMyProfilePropertiesFile("myprofile.title");
		if(driver.getTitle().contains(expectedTitle)) {
			isMyProfilePageOpened = true;
		}
		return isMyProfilePageOpened;
	}
	
	public MySettingsPage goToMySettings(WebDriver driver) {
		this.mySettings.click();
		return new MySettingsPage(driver);
	}
	
	//Method will validate my settings page
	public boolean validateMySettingsPage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isMySettingsPageOpened = false;
		String expectedTitle = FileUtils.readMySettingsPropertiesFile("mysettings.title");
		if(driver.getTitle().contains(expectedTitle)) {
			isMySettingsPageOpened = true;
		}
		return isMySettingsPageOpened;
	}
	
	public DeveloperConsole goToDeveloperConsole(WebDriver driver) {
		this.developerConsole.click();
		return new DeveloperConsole(driver);
	}
	
	//Method to validate the developer console window title
	public boolean validateDevConsoleWindowTitle(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isWindowTitleMatched = false;
		String expectedTitle = FileUtils.readDeveloperConsolePropertiesFile("devconsole.windowtitle");
		if (driver.getTitle().contains(expectedTitle)) {
			isWindowTitleMatched = true;
		}
		return isWindowTitleMatched;
	}
		
	
	public LoginPage clickLogout(WebDriver driver) {
		this.logout.click();
		return new LoginPage(driver);
	}
	
	public AccountsTabPage clickOnAccountsTab(WebDriver driver) {
		waitUtils.waitForElementToBeClickable(driver, this.accountsTab, 20);
		this.accountsTab.click();
		return new AccountsTabPage(driver);
	}
	
	public boolean validateAccountsTabPage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isAccountsPageOpened = false;
		waitUtils.waitForTitle(driver, FileUtils.readAccountsTabPropertiesFile("accountstab.title"), 25);
		String expectedTitle = FileUtils.readAccountsTabPropertiesFile("accountstab.title");
		
		if(driver.getTitle().contains(expectedTitle)) {
			isAccountsPageOpened = true;
		}
		return isAccountsPageOpened;
	}
	
}
