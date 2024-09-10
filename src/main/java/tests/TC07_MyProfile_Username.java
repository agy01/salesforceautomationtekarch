package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;
import utils.FileUtils;
import utils.waitUtils;

public class TC07_MyProfile_Username extends BaseTest {

		@Test
		public void validateMyProfieOption() throws FileNotFoundException, IOException, InterruptedException {
		
		//Launch the application
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		
		//Login to the application
		lp.loginToApplication(driver, FileUtils.readLoginPropertiesFile("valid.username"), FileUtils.readLoginPropertiesFile("valid.password"));
		
		//Wait for the usermenu drop down and click
		waitUtils.waitForElementToBeVisible(driver, hp.userMenuDropDown, 20);
		hp.clickUserMenu();
		
		//Validate the user menu options
		hp.areAllOptionsPresent();
		
		//Click on My profile from the user menu and click on edit profile on my profile page
		hp.goToMyProfile(driver);
		waitUtils.waitForTitle(driver, FileUtils.readMyProfilePropertiesFile("myprofile.title"), 20);
		Assert.assertEquals(driver.getTitle(), FileUtils.readMyProfilePropertiesFile("myprofile.title"), "Actual and expected Title of my profile page should be same");
		myProfile.clickOnEditProfile();
		
		//Validate if the Pop up window has about and contact tab
		boolean areTheTabsDisplayed = myProfile.areAboutTabAndContactTabisDispalyed();
		Assert.assertTrue(areTheTabsDisplayed, "About Tab and Contact Tab should be present in the pop up window");
		
		//Click on about tab and enter the lastname 
		myProfile.goToAboutTab();
		myProfile.editlastnameInAboutTab(FileUtils.readMyProfilePropertiesFile("about.lastname"));
		
		myProfile.pageRefresh();
		
		//validate username on the profile page
		boolean isUpdated = myProfile.isUsernameUpdated(FileUtils.readMyProfilePropertiesFile("userprofile.username"));
		Assert.assertTrue(isUpdated, "Username Should be update in my profile page");
		
		//Click on post and share the post message to feeds
		myProfile.postAMessage(FileUtils.readMyProfilePropertiesFile("post.message"));
		Thread.sleep(2000);
		
		//Validate the posted message on the feeds
		boolean isMessagePosted = myProfile.isPostShared(FileUtils.readMyProfilePropertiesFile("post.message"));
		Assert.assertTrue(isMessagePosted, "Message should be posted in the feeds");
		
		//Click on file link and post a file on the feed
		myProfile.shareAFile(FileUtils.readMyProfilePropertiesFile("upload.filepath"));
		Thread.sleep(2000);
		
		myProfile.pageRefresh();
		//Validate if the shared file in the feed against expected file
		boolean isFiledisplayed = myProfile.isFileShared(FileUtils.readMyProfilePropertiesFile("upload.filename"));
		Assert.assertTrue(isFiledisplayed, "Uploded file and the file in feed should be same");
		
		//Upload the profile picture
		myProfile.addProfilePhoto(FileUtils.readMyProfilePropertiesFile("upload.profilephoto.path"));
		myProfile.cropProfilePhoto();
		
		//Validate the uplodedPhoto
		boolean isPhotoUploaded = myProfile.isPhotoUploaded();
		Assert.assertTrue(isPhotoUploaded, "Profile photo should be uploaded");
		
		System.out.println("TC07_MyProfile_Username Testing completed");
		
	}

}
