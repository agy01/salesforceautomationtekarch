package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
public class UserMenuTest1 extends BaseTest{
	
	@Test
	public void validateUserMenuDropDown() throws FileNotFoundException, IOException {
//		WebDriver driver = getDriver();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match");
		test.get().info("Clicking on usermenu drop down");
		hp.clickUserMenu(getDriver());
		test.get().info("Validating is all options are present");
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present in the usermenu drop down");
		System.out.println("TC06_validateUserMenuDropDown Testing is completed");
		logger.info("TC06_validateUserMenuDropDown testing is completed");
	}
	
	@Test
	public void validateMyProfieOption() throws FileNotFoundException, IOException, InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match");
		test.get().info("Clicking on usermenu drop down");
		hp.clickUserMenu(getDriver());
		test.get().info("Validating is all options are present");
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present in the usermenu drop down");
		test.get().info("Click on my profile option");
		hp.goToMyProfile(getDriver());
		test.get().info("Validating my profile page");
		Assert.assertTrue(hp.validateMyProfilePage(getDriver()), "Actual and Expected my profile page title should match");
		MyProfilePage myProfile = new MyProfilePage(getDriver());
		test.get().info("Clicking on edit profile..");
		myProfile.clickOnEditProfile(getDriver());
		test.get().info("Validating About Tab and Contact Tab on the new window");
		myProfile.areAboutTabAndContactTabisDispalyed(getDriver());
		test.get().info("Clicking on About Tab..");
		myProfile.goToAboutTab();
		test.get().info("Entering the last name...");
		myProfile.editlastnameInAboutTab(getDriver());
		test.get().info("Validateing if username is updated...");
		Assert.assertTrue(myProfile.isUsernameUpdated(), "Username should be updated in my profile page");
		test.get().info("Posting the message...");
		myProfile.postAMessage(getDriver());
		Thread.sleep(2000);
		test.get().info("Validatin the message posted");
		Assert.assertTrue(myProfile.isPostShared(getDriver()), "Message should be posted");
		test.get().info("Sharing a file...");
		myProfile.shareAFile();
		Thread.sleep(2000);
		test.get().info("Validating the shared file");
		Assert.assertTrue(myProfile.isFileShared(getDriver()), "File should be shared");
		test.get().info("Selecting the profile photo");
		myProfile.addProfilePhoto(getDriver());
		test.get().info("Croping the profile photo..");
		myProfile.cropProfilePhoto(getDriver());
		test.get().info("Validate the photo uploaded");
		Assert.assertTrue(myProfile.isPhotoUploaded(getDriver()), "Profile photo should be uploaded");
		System.out.println("TC07_validateMyProfieOption Testing completed");
		logger.info("TC07_validateMyProfieOption testing is completed");
	}
	
	@Test
	public void validateMySettingOption() throws FileNotFoundException, IOException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match");
		test.get().info("Clicking on usermenu drop down");
		hp.clickUserMenu(getDriver());
		test.get().info("Validating is all options are present");
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present in the usermenu drop down");
		test.get().info("Clicking on my settings option");
		hp.goToMySettings(getDriver());
		test.get().info("Validating my settings page");
		Assert.assertTrue(hp.validateMySettingsPage(getDriver()), "Actual and Expected my Settings page title should match");
		MySettingsPage mySettings = new MySettingsPage(getDriver());
		test.get().info("Click on personal link");
		mySettings.clickOnPersonalLink(getDriver());
		test.get().info("Validate the personal login history page");
		mySettings.validatePersonalLinkLoginHistoryPage(getDriver());
		test.get().info("Click on Download link");
		mySettings.clickOnDownloadLoginHistory(getDriver());
		test.get().info("Validate if file is downloaded");
		Assert.assertTrue(mySettings.isfiledownloaded(), "Login history should be downloaded");
		test.get().info("Selecting salesforce chartter in the diaplay and layout");
		mySettings.setupDisplayAndLayout(getDriver());
		test.get().info("Validating if report is added to selected tab");
		mySettings.addReportsToSelectedTab(getDriver());
		test.get().info("Clicking on mail setting...");
		mySettings.setupEmailSettings();
		test.get().info("Validate the email setting saved");
		Assert.assertTrue(mySettings.isEmailSettingsSaved(), "Email settings should be saved"); 
//		test.get().info("Trigger reminder in calender...");
//		mySettings.goToCalenderReminderandTriggerTestReminder();
//		test.get().info("Validate if pop up window is displayed");
//		mySettings.isReminderWindowPopUp(getDriver());
		System.out.println("TC08_validateMySettingOption Testing Completed");
		logger.info("TC08_validateMySettingOption testing is completed");
	}
	
	@Test
	public void validateDeveloperConsoleOption() throws FileNotFoundException, IOException, InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match");
		test.get().info("Clicking on usermenu drop down");
		hp.clickUserMenu(getDriver());
		test.get().info("Validating is all options are present");
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present in the usermenu drop down");
		test.get().info("Clicking on Devloper console option");
		hp.goToDeveloperConsole(getDriver());
		DeveloperConsole devConsole = new DeveloperConsole(getDriver());
		test.get().info("Validating if Devloper console window is opened");
		Assert.assertTrue(devConsole.switchToDeveloperConsole(getDriver()), "Developer console window should be displayed");
		test.get().info("Validtaing Devloper console window title");
		Assert.assertTrue(hp.validateDevConsoleWindowTitle(getDriver()),
				"Dev console window title should match with the expected title");
		Thread.sleep(2000);
		test.get().info("Closing the devloper console window");
		devConsole.closeTheDeveloperConsole(getDriver());
		System.out.println("TC09_validateDeveloperConsoleOption Testing completed");
		logger.info("TC09_validateDeveloperConsoleOption testing is completed");
	}
	
	@Test
	public void validateLogoutOption() throws FileNotFoundException, IOException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match");
		test.get().info("Clicking on usermenu drop down");
		hp.clickUserMenu(getDriver());
		test.get().info("Validating is all options are present");
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present in the usermenu drop down");
		test.get().info("Clicking on Logout option");
		hp.clickLogout(getDriver());
		test.get().info("Validting logout page title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Login page title should match");
		System.out.println("TC10_validateLogoutOption Testing Completed");
		logger.info("TC10_validateLogoutOption testing is completed");
	}

}
