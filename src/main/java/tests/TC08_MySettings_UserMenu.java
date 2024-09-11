package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;

public class TC08_MySettings_UserMenu extends BaseTest {

		@Test
		public void validateMySettingOption() throws FileNotFoundException, IOException {
		
		//Launch and login to the application
		lp.launchApplication();
		lp.loginToApplication();
		
		//Click on the user menu drop down and validate
		hp.clickUserMenu();
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present as expected"); 
		
		//Click on My settings option and Validate the title
		hp.goToMySettings();
		Assert.assertTrue(hp.validateMySettingsPage(), "My Settings page title should be matched");
		
		//Click on personal link and select login history in my settings and validate the title of login history page
		mySettings.clickOnPersonalLink();
		Assert.assertTrue(mySettings.validatePersonalLinkLoginHistoryPage(), "Login history page should be dispalyed");
		
		//click on download login history link
		mySettings.clickOnDownloadLoginHistory();
		Assert.assertTrue(mySettings.isfiledownloaded(), "Login History file should be downloaded to local system");
		
		//Click on Display and layouts link, click customize my tabs and select salesforce Chatter
		mySettings.setupDisplayAndLayout();
				
		//Search for reports element in availableTab and adds to the selectedTab
		Assert.assertTrue(mySettings.addReportsToSelectedTab(), "Reports tab should be added to selectedTab"); 
		
		//Enter the email name, email address and save
		mySettings.setupEmailSettings();
		
		//Validate if the email settings is saved
		Assert.assertTrue(mySettings.isEmailSettingsSaved(), "Email settings should be saved"); 
		
		//Trigger a reminder under Calender and Reminder
		mySettings.goToCalenderReminderandTriggerTestReminder();
		
		//Validate if the window pops up
//		mySettings.switchToPopUpWindow();
//		boolean reminderWindowPopUp = mySettings.isReminderWindowPopUp();
		Assert.assertTrue(mySettings.isReminderWindowPopUp(), "Window should pop up to trigger reminder");
		
//		mySettings.switchBackToMainWindow();
		
		System.out.println("TC08_MySettings_UserMenu Testing Completed");

	}

	
}
