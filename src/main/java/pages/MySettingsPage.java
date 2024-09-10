package pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ActionUtils;
import utils.waitUtils;

public class MySettingsPage {
	
	protected WebDriver driver;
	
	public MySettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//span[@id=\"PersonalInfo_font\"]")
	public WebElement personalLink;
		@FindBy(xpath = "//span[@id=\"LoginHistory_font\"]")
		public WebElement loginHistory;
		@FindBy(xpath = "//div[@class=\"pShowMore\"]/a")
		public WebElement downloadLoginHostory;
		
	@FindBy(xpath = "//span[@id=\"DisplayAndLayout_font\"]")
	public WebElement displayAndLayout;
		@FindBy(xpath = "//span[@id=\"CustomizeTabs_font\"]")
		public WebElement customizeMyTabs;
		@FindBy(xpath = "//select[@id=\"p4\"]")
		public WebElement customAppDropdown;
			@FindBy(xpath = "//select[@id=\"p4\"]/option")
			public List<WebElement> dropdownOptions;
		@FindBy(xpath = "//select[@id=\"duel_select_0\"]")
		public WebElement availableTabs;
			@FindBy(xpath = "//img[@class=\"rightArrowIcon\"]")
			public WebElement addButton;
			@FindBy(xpath = "//img[@class=\"leftArrowIcon\"]")
			public WebElement removeButton;
		@FindBy(xpath = "//select[@id=\"duel_select_1\"]")
		public WebElement selectedTabs;
			@FindBy(xpath = "//input[@value=\" Save \"]")
			public WebElement saveButton;
		
	@FindBy(xpath = "//span[@id=\"EmailSetup_font\"]")
	public WebElement email;
		@FindBy(xpath = "//span[@id=\"EmailSettings_font\"]")
		public WebElement myEmailSettings;
			@FindBy(xpath = "//input[@id=\"sender_name\"]")
			public WebElement emailName;
			@FindBy(xpath = "//input[@id=\"sender_email\"]")
			public WebElement emailAddress;
			@FindBy(xpath = "//input[@id=\"auto_bcc1\"]")
			public WebElement AutomaticBccYesRadioButton;
			@FindBy(xpath = "//input[@value=\" Save \"]")
			public WebElement saveEmailSettings;
			@FindBy(xpath = "//div[@id=\"errorDiv_ep\"]")
			public WebElement errorSavingSettings;
			@FindBy(xpath = "//div[@id=\"meSaveCompleteMessage\"]")
			public WebElement saveCompleteConfirmation;
			
			
	@FindBy(xpath = "//span[@id=\"CalendarAndReminders_font\"]")
	public WebElement calenderAndReminders;
		@FindBy(xpath = "//span[@id=\"Reminders_font\"]")
		public WebElement activityReminder;
			@FindBy(xpath = "//input[@id=\"testbtn\"]")
			public WebElement testReminder;
				@FindBy(tagName = "form")
				public WebElement reminderPopUp;

	//Method will Navigate to personal link and then select Login History 
	public void clickOnPersonalLink() {
		this.personalLink.click();
		ActionUtils.mouseHover(driver, this.loginHistory);
	}
	
	//Method will click on download login history link
	public void clickOnDownloadLoginHistory() {
		this.downloadLoginHostory.click();
	}
	
	//Method will validate if the file downloaded in local system is as expected
	public boolean isfiledownloaded(String downloadsDir, String fileName) {
		File dir = new File(downloadsDir);
		File[] files = dir.listFiles((d, name) -> name.startsWith(fileName) && name.endsWith(".csv"));
		System.out.println("Login History File is downloaded");
		return files != null && files.length > 0;
	}
	
	//Method will Navigate to display & layout, customize my tabs and from the custom app selects salesforce chatter
	public void setupDisplayAndLayout() {
		this.displayAndLayout.click();
		this.customizeMyTabs.click();
		this.customAppDropdown.click();
		ActionUtils.selectDropDownValue(driver, dropdownOptions, "Salesforce Chatter");
	}
	
	//Method will add the reports element from the available tabs to selected tab
	public void addReportsToSelectedTab() {
		boolean isReportsAlreadyInSelectedTab = ActionUtils.isElementPresentInList(this.selectedTabs, "Reports");
		if(!isReportsAlreadyInSelectedTab) {
			ActionUtils.selectElementInList(this.availableTabs, "Reports");
			this.addButton.click();
		}
		this.saveButton.click();
		System.out.println("Reports tab is added to selectedTab");
	}
	
	//Method will navigate to Email, email settings and enter the details and save
	public void setupEmailSettings(String senderName, String senderEmailAddress) {
		this.email.click();
		this.myEmailSettings.click();
		this.emailName.clear();
		this.emailName.sendKeys(senderName);
		this.emailAddress.clear();
		this.emailAddress.sendKeys(senderEmailAddress);
		this.AutomaticBccYesRadioButton.click();
		this.saveEmailSettings.click();
	}
	
	//Method will validate if the Email settings are saved
	public boolean isEmailSettingsSaved(String expectedTextMessage) {
			if(this.saveCompleteConfirmation.isDisplayed()) {
			String actualTextMessage = this.saveCompleteConfirmation.getText();
			return actualTextMessage.equals(expectedTextMessage);
		}
		return false;
	}
	
	//Method will navigate to the calender and reminder and trigger the test reminder
	public void goToCalenderReminderandTriggerTestReminder() {
		this.calenderAndReminders.click();
		this.activityReminder.click();
		this.testReminder.click();
	}
	
	//Method will validate if the window pops up
	public boolean isReminderWindowPopUp() {
		ActionUtils.switchToPopUpWindow(driver);
		waitUtils.waitForElementToBeVisible(driver, this.reminderPopUp, 30);
		ActionUtils.switchBackToMainWindow(driver);
		return reminderPopUp.isDisplayed();
	}
	
	
}
