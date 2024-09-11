package pages;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import contants.FileConstants;
import utils.ActionUtils;
import utils.FileUtils;
import utils.waitUtils;

public class MyProfilePage {
	
	//initializing the driver globally
	protected WebDriver driver;
	
	public MyProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//a[@class=\"contactInfoLaunch editLink\"]/img")
	public WebElement editProfile;
		@FindBy(xpath ="//iframe[@id=\"contactInfoContentId\"]")
		public WebElement editProfileIframe;
			@FindBy(xpath = "//li[@id=\"aboutTab\"]/a")
			public WebElement aboutTab;
				@FindBy(xpath = "//input[@id=\"lastName\"]")
				public WebElement lastname;
				@FindBy(xpath = "//input[@value=\"Save All\"]")
				public WebElement saveButton;
			@FindBy(xpath = "//li[@id=\"contactTab\"]/a")
			public WebElement contactTab;
		@FindBy(xpath = "//span[@id=\"tailBreadcrumbNode\"]")
		public WebElement usernameInProfilePage;
	
	
	@FindBy(xpath =  "//a[@id=\"publisherAttachTextPost\"]/span[1]")
	public WebElement post;
		@FindBy(xpath = "//iframe[@class=\"cke_wysiwyg_frame cke_reset\"]")
		public WebElement postWindowIframe;
		@FindBy(xpath =  "//body[@role=\"textbox\"]")
		public WebElement messageField;
		@FindBy(xpath =  "//input[@id=\"publishersharebutton\"]")
		public WebElement sharePost;
		
		
	@FindBy(xpath = "//div[@id=\"feedwrapper\"]")
	public WebElement postFeed;
		
	
	@FindBy(xpath = "//a[@id=\"publisherAttachContentPost\"]/span[1]")
	public WebElement file;
		@FindBy(id="chatterLinkFileAction")
		public WebElement selectfileFromSalesforce;
		@FindBy(xpath="//td[@id=\"chatterUploadFileActionPanel\"]")
		public WebElement uploadfileFromComputer;
			@FindBy(xpath = "//input[@id=\"chatterFile\"]")
			public WebElement chooseFile;
			@FindBy(xpath = "//span[@class=\"publisherShareButtonPlaceholder\"]")
			public WebElement sharefile;

			
	@FindBy(xpath = "//a[@id=\"uploadLink\"]")
	public WebElement addPhoto;
		@FindBy(id = "uploadPhotoContentId")
		public WebElement addPhotoIframe;
			@FindBy(xpath = "//input[@id=\"j_id0:uploadFileForm:uploadInputFile\"]")
			public WebElement choosePhoto;
			@FindBy(xpath = "//input[@id=\"j_id0:uploadFileForm:uploadBtn\"]")
			public WebElement savePhoto;
				@FindBy(id = "j_id0:j_id7:save")
				public WebElement saveCropPhoto;
				
		
	
	/**
	 * clicks on edit profile icon and switch to iframe
	 */
	public void clickOnEditProfile() {
		this.editProfile.click();
		driver.switchTo().frame(editProfileIframe);
	}
	
	
	/**
	 * validates if the window has about and contact tabs and switch back to main frame
	 * @return
	 */
	public boolean areAboutTabAndContactTabisDispalyed() {
		boolean areTheTabsDisplayed = false;
		try {
			waitUtils.waitForElementToBeVisible(driver, aboutTab, 10);
			waitUtils.waitForElementToBeVisible(driver, contactTab, 5);
			Thread.sleep(2000);
			if(aboutTab.isDisplayed() && contactTab.isDisplayed()) {
				return areTheTabsDisplayed = true;
			}
		} catch (Exception e) {
	        System.out.println("Exception while checking tabs inside iframe: " + e.getMessage());
	        // Ensure we switch back to the main content in case of any errors
	        driver.switchTo().defaultContent();
	    }
		return areTheTabsDisplayed;
	}
	
	public void goToAboutTab(){
		this.aboutTab.click();
		
	}
	
	//Enters the lastname in the About tab and switch back to the main frame
	public void editlastnameInAboutTab() throws FileNotFoundException, IOException {
		String lastname = FileUtils.readMyProfilePropertiesFile("about.lastname");
		this.lastname.clear();
		this.lastname.sendKeys(lastname);
		this.saveButton.click();
		driver.switchTo().defaultContent();
	}
	
	
	//waits for the user name to be visible in profile page and validate with expected username
	public boolean isUsernameUpdated() throws FileNotFoundException, IOException {
		String lastname = FileUtils.readMyProfilePropertiesFile("about.lastname");
		boolean isLastNameChanged = true;
		if(!this.usernameInProfilePage.getText().contains(lastname)) {
			isLastNameChanged = false;
		}
		return isLastNameChanged;
	}
	
	//clicks on post, enters the text message and shares
	public void postAMessage() {
		try {
		this.post.click();
		driver.switchTo().frame(postWindowIframe);
		this.messageField.click();
		waitUtils.waitForElementToBeClickable(driver, this.messageField, 25);
		String message = FileUtils.readMyProfilePropertiesFile("post.message");
		this.messageField.sendKeys(message);
		driver.switchTo().defaultContent();
		this.sharePost.click();
		}catch (Exception e) {
			System.out.println("Exception occured while posting a message: " + e.getMessage());
		}
	}
	
	//Validates the message posted in the feed
	public boolean isPostShared() throws FileNotFoundException, IOException {
		boolean isMessagePosted = false;
		String expectedMessage = FileUtils.readMyProfilePropertiesFile("post.message");
		WebElement sharedPost = waitUtils.visibilityOfElementLocated(driver, By.xpath("//span/p[contains(text(), '" + expectedMessage + "')]"));
		String actualPost = sharedPost.getText();
		if(actualPost.equals(expectedMessage)){
			System.out.println("Post is Shared");
			return isMessagePosted = true;
		}
		return isMessagePosted;
	}
	
	//Click on file link, send the file path and share the file
	public void shareAFile() {
		this.file.click();
		this.uploadfileFromComputer.click();
		this.chooseFile.sendKeys(FileConstants.TEST_FILE_UPLOAD_PATH);
		this.sharefile.click();
	}
	
	
	//Validate the shared file on the feed
	public boolean isFileShared() throws FileNotFoundException, IOException {
		boolean isFileUploaded = false;
		String expectedfilename = FileUtils.readMyProfilePropertiesFile("upload.filename");
		WebElement fileInFeed = waitUtils.visibilityOfElementLocated(driver, By.xpath("//span[contains(text(), '" + expectedfilename +"')]"));
		String actualfilename = fileInFeed.getText();
		if(actualfilename.equals(expectedfilename)) {
			System.out.println("File is uploaded");
			return isFileUploaded = true;
		}
		return isFileUploaded;
	}
	
	//Click on add photo and switch to iframe and save the photo
	public void addProfilePhoto() {
		ActionUtils.mouseHover(driver, this.addPhoto);
		driver.switchTo().frame(addPhotoIframe);
		waitUtils.waitForElementToBeVisible(driver, this.choosePhoto, 20);
		this.choosePhoto.sendKeys(FileConstants.TEST_PHOTO_UPLOAD_PATH);
		this.savePhoto.click();
	}
	
	//crop the selected photo and switches back to the main frame
	public void cropProfilePhoto() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		WebElement cropHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"imgCrop_handle imgCrop_handleNW\"]/following-sibling::div")));
		WebElement cropHandle = waitUtils.visibilityOfElementLocated(driver, By.xpath("//div[@class=\"imgCrop_handle imgCrop_handleNW\"]/following-sibling::div"));
		Actions action = new Actions(driver);
		action.clickAndHold(cropHandle).moveByOffset(100, 50).release().perform();
		
		this.saveCropPhoto.click();
		driver.switchTo().defaultContent();
	}
	
	//Validates if the photo is uploaded
	public boolean isPhotoUploaded() {
		boolean isPhotoDisplayed = false;
		WebElement photoUploaded = waitUtils.visibilityOfElementLocated(driver,
				By.xpath("//span[@class=\"profileImage chatter-avatarFull chatter-avatar\"]"));
		if (photoUploaded.isDisplayed()) {
			System.out.println("Photo is uploaded");
			return isPhotoDisplayed = true;
		}
		return isPhotoDisplayed;
	}
	

}
