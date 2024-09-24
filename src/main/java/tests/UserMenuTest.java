package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.ListenersSFDC;
import pages.DeveloperConsole;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.MySettingsPage;
import utils.BaseTest;

@Listeners(ListenersSFDC.class)
public class UserMenuTest extends BaseTest {

	protected LoginPage lp = null;
	protected HomePage hp = null;
	protected MyProfilePage myProfile = null;
	protected MySettingsPage mySettings = null;
	protected DeveloperConsole devConsole = null;
	
	@BeforeMethod
	public void homePage() {
		lp = new LoginPage(getDriver());
		hp = new HomePage(getDriver());
		myProfile = new MyProfilePage(getDriver());
		mySettings = new MySettingsPage(getDriver());
		devConsole = new DeveloperConsole(getDriver());
	}
	
	@Test
	public void validateUserMenuDropDown() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Entering valid creads...");
		lp.loginToApplication(driver);
		hp.validateHomePageTitle(driver);
		test.get().info("Validate home page title");
		test.get().info("Clicking on usermenu drop down");
		hp.clickUserMenu(driver);
		hp.areAllOptionsPresent();
		test.get().info("Validating is all options are present");
		System.out.println("TC06_ValidateUserMenuDropDown Testing is completed");

	}

	@Test
	public void validateMyProfieOption() throws FileNotFoundException, IOException, InterruptedException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Entering valid creads...");
		lp.loginToApplication(driver);
		hp.validateHomePageTitle(driver);
		test.get().info("Validate home page title");
		test.get().info("Clicking on usermenu drop down");
		hp.clickUserMenu(driver);
		hp.areAllOptionsPresent();
		test.get().info("Validating is all options are present");
		hp.goToMyProfile(driver);
		test.get().info("Clicked on my profile");
		hp.validateMyProfilePage(driver);
		test.get().info("Validating my profile page");
		myProfile.clickOnEditProfile(driver);
		test.get().info("Clicked on edit profile");
		myProfile.areAboutTabAndContactTabisDispalyed(driver);
		test.get().info("Validating About Tab and Contact Tab on the new window");
		test.get().info("Clicking on About Tab");
		myProfile.goToAboutTab();
		test.get().info("Entering the last name...");
		myProfile.editlastnameInAboutTab(driver);
		myProfile.isUsernameUpdated();
		test.get().info("Validate if username is updated");
		test.get().info("Posting the message...");
		myProfile.postAMessage(driver);
		Thread.sleep(2000);
		myProfile.isPostShared(driver);
		test.get().info("Validatin the message posted");
		test.get().info("Sharing a file...");
		myProfile.shareAFile();
		Thread.sleep(2000);
		myProfile.isFileShared(driver);
		test.get().info("Validatin the shared file");
		myProfile.addProfilePhoto(driver);
		test.get().info("Selected the profile photo");
		myProfile.cropProfilePhoto(driver);
		test.get().info("Croping the profile photo..");
		myProfile.isPhotoUploaded(driver);
		test.get().info("Validate the photo uploaded");
		System.out.println("TC07_MyProfile_Username Testing completed");

	}

//	@Test
	public void validateMySettingOption() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Entering valid creads...");
		lp.loginToApplication(driver);
		hp.validateHomePageTitle(driver);
		test.get().info("Validate home page title");
		test.get().info("Clicking on usermenu drop down");
		hp.clickUserMenu(driver);
		hp.areAllOptionsPresent();
		test.get().info("Validating is all options are present");
		hp.goToMySettings(driver);
		test.get().info("Clicked on my settings option");
		hp.validateMySettingsPage(driver);
		test.get().info("Validating my settings page");
		test.get().info("Click on personal link");
		mySettings.clickOnPersonalLink(driver);
		mySettings.validatePersonalLinkLoginHistoryPage(driver);
		test.get().info("Validate the personal login history page");
		mySettings.clickOnDownloadLoginHistory(driver);
		test.get().info("Click on Download link");
		mySettings.isfiledownloaded();
		test.get().info("Validate if file is downloaded");
		mySettings.setupDisplayAndLayout(driver);
		test.get().info("Selected salesforce chartter in the diaplay and layout");
		test.get().info("Validating if report is added to selected tab");
		mySettings.addReportsToSelectedTab(driver);
		test.get().info("Clicking on mail setting...");
		mySettings.setupEmailSettings();
		mySettings.isEmailSettingsSaved();
		test.get().info("Validate the email setting saved");
		test.get().info("Trigger reminder in calender...");
		mySettings.goToCalenderReminderandTriggerTestReminder();
		mySettings.isReminderWindowPopUp(driver);
		test.get().info("Validate if pop up window is displayed");
		System.out.println("TC08_MySettings_UserMenu Testing Completed");

	}

//	@Test
	public void validateDeveloperConsoleOption() throws FileNotFoundException, IOException, InterruptedException {
		WebDriver driver = getDriver();
		// Launch and login to the application
		lp.launchApplication(driver);
		lp.loginToApplication(driver);
		// Click user menu drop down and validate if all expected options are present
		hp.clickUserMenu(driver);
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present as expected");
		// Select Developer console from the options of user menu
		hp.goToDeveloperConsole(driver);
		// Validates Window pops up and switch to the new window
		Assert.assertTrue(devConsole.switchToDeveloperConsole(driver), "Developer console window should be displayed");
		// Validates Window title
		Assert.assertTrue(hp.validateDevConsoleWindowTitle(driver),
				"Dev console window title should match with the expected title");
		Thread.sleep(2000);
		// Window closed and switch back to main window
		devConsole.closeTheDeveloperConsole(driver);
		System.out.println("TC09_DeveloperConsole_UserMenu Testing completed");
	}

//	@Test
	public void validateLogoutOption() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		// Launch Application and login
		lp.launchApplication(driver);
		lp.loginToApplication(driver);
		// Click on userMenu drop down and validate if all options are present
		hp.clickUserMenu(driver);
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present as expected");
		// Click on logout option from usermenu
		hp.clickLogout(driver);
		// Match the page title by waiting for 20 seconds
		Assert.assertTrue(lp.validatePageTitle(driver), "Login page title should match");
		System.out.println("TC10_Logout_UserMenu Testing Completed");
	}

}
