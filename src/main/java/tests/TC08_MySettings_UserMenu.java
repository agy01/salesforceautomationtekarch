package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;
import utils.FileUtils;
import utils.waitUtils;

public class TC08_MySettings_UserMenu extends BaseTest {

		@Test
		public void validateMySettingOption() throws FileNotFoundException, IOException {
		
		//Launch the application
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		//Login to the application
		lp.loginToApplication(driver, FileUtils.readLoginPropertiesFile("valid.username"), FileUtils.readLoginPropertiesFile("valid.password"));
		
		//Click on the usermenu drop down
		waitUtils.waitForElementToBeClickable(driver, hp.userMenuDropDown, 20);
		hp.clickUserMenu();
		//Validate the usermenu options with the expectedoptions
		hp.areAllOptionsPresent();
		
		//Click on My settings option and Validate the title
		hp.goToMySettings();
		waitUtils.waitForTitle(driver, FileUtils.readMySettingsPropertiesFile("mysettings.title"), 20);
		Assert.assertEquals(driver.getTitle(), FileUtils.readMySettingsPropertiesFile("mysettings.title"), "Actual and expected Title of my setting page should match");
		
		//Click on personal link and select login history in my settings and validate the title of login history page
		mySettings.clickOnPersonalLink();
		waitUtils.waitForTitle(driver, FileUtils.readMySettingsPropertiesFile("loginhistory.title"), 20);
		Assert.assertEquals(driver.getTitle(), FileUtils.readMySettingsPropertiesFile("loginhistory.title"), "Actual and expected title of login history should be same");
		
		//click on download login history link
		mySettings.clickOnDownloadLoginHistory();
		mySettings.isfiledownloaded(FileUtils.readMySettingsPropertiesFile("downloads.directory"), "LoginHistory");
		
		
		//Click on Display and layouts link, click customize my tabs and select salesforce Chatter
		mySettings.setupDisplayAndLayout();
				
		//Search for reports element in availableTab and adds to the selectedTab
		mySettings.addReportsToSelectedTab();
		
		//Enter the email name, email address and save
		mySettings.setupEmailSettings(FileUtils.readMySettingsPropertiesFile("sender.emailname"), FileUtils.readMySettingsPropertiesFile("sender.emailaddress"));
		
		//Validate if the email settings is saved
		mySettings.isEmailSettingsSaved(FileUtils.readMySettingsPropertiesFile("emailsettings.saved.confirmationmessage"));
		
		//Trigger a reminder under Calender and Reminder
		mySettings.goToCalenderReminderandTriggerTestReminder();
		
		//Validate if the window pops up
//		mySettings.switchToPopUpWindow();
		boolean reminderWindowPopUp = mySettings.isReminderWindowPopUp();
		Assert.assertTrue(reminderWindowPopUp, "Window should pop up to trigger reminder");
		
//		mySettings.switchBackToMainWindow();
		
		System.out.println("TC08_MySettings_UserMenu Testing Completed");

	}

	
}
