package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;

public class TC07_MyProfile_Username extends BaseTest {

		@Test
		public void validateMyProfieOption() throws FileNotFoundException, IOException, InterruptedException {
		
		//Launch and login to the application
		lp.launchApplication();	
		lp.loginToApplication();
		
		//Wait for the usermenu drop down and click
		hp.clickUserMenu();
		
		//Validate the user menu options
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present as expected"); 
		
		//Click on My profile from the user menu and click on edit profile on my profile page
		hp.goToMyProfile();
		Assert.assertTrue(hp.validateMyProfilePage(), "My Profile page should be opened");
		myProfile.clickOnEditProfile();
		
		//Validate if the Pop up window has about and contact tab
//		boolean areTheTabsDisplayed = myProfile.areAboutTabAndContactTabisDispalyed();
		Assert.assertTrue(myProfile.areAboutTabAndContactTabisDispalyed(), "About Tab and Contact Tab should be present in the pop up window");
		
		//Click on about tab and enter the lastname 
		myProfile.goToAboutTab();
		myProfile.editlastnameInAboutTab();
			
		//validate username on the profile page
		Assert.assertTrue(myProfile.isUsernameUpdated(), "Username in the profile page should be updated");
		
		//Click on post and share the post message to feeds
		myProfile.postAMessage();
		Thread.sleep(2000);
		
		//Validate the posted message on the feeds
		Assert.assertTrue(myProfile.isPostShared(), "Message should be posted in the feeds");
		
		//Click on file link and post a file on the feed
		myProfile.shareAFile();
		Thread.sleep(2000);
		
		//Validate if the shared file in the feed against expected file
		Assert.assertTrue(myProfile.isFileShared(), "Uploded file and the file in feed should be same");
		
		//Upload the profile picture
		myProfile.addProfilePhoto();
		myProfile.cropProfilePhoto();
		
		//Validate the uplodedPhoto
		Assert.assertTrue(myProfile.isPhotoUploaded(), "Profile photo should be uploaded");
		
		System.out.println("TC07_MyProfile_Username Testing completed");
		
	}

}
