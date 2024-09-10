package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ActionUtils;
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
				
		
	
	
	
	
	//clicks on edit profile icon and switch to iframe
	public void clickOnEditProfile() {
		this.editProfile.click();
		driver.switchTo().frame(editProfileIframe);
	}
	
	//validates if the window has about and contact tabs and switch back to main frame
	public boolean areAboutTabAndContactTabisDispalyed() {
		try {
			waitUtils.waitForElementToBeVisible(driver, aboutTab, 10);
			waitUtils.waitForElementToBeVisible(driver, contactTab, 5);
			Thread.sleep(2000);
			boolean result = aboutTab.isDisplayed() && contactTab.isDisplayed();
			return result;
		} catch (Exception e) {
	        System.out.println("Exception while checking tabs inside iframe: " + e.getMessage());

	        // Ensure we switch back to the main content in case of any errors
	        driver.switchTo().defaultContent();
	        return false;
	    }
	}
	
	public void goToAboutTab(){
		this.aboutTab.click();
		
	}
	
	//Enters the lastname in the About tab and switch back to the main frame
	public void editlastnameInAboutTab(String lastname) {
		this.lastname.clear();
		this.lastname.sendKeys(lastname);
		this.saveButton.click();
		driver.switchTo().defaultContent();
	}
	
	public void pageRefresh() {
		driver.navigate().refresh();
	}
	
	//waits for the user name to be visible in profile page and validate with expected username
	public boolean isUsernameUpdated(String expectedUsername) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		WebElement updatedUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id=\"tailBreadcrumbNode\" and contains(text(), '" + expectedUsername.trim()  + "')]")));
		WebElement updatedUsername = waitUtils.visibilityOfElementLocated(driver, By.xpath("//span[@id=\"tailBreadcrumbNode\" and contains(text(), '" + expectedUsername.trim()  + "')]"));
		String actualUsername = updatedUsername.getText();
		System.out.println("UserName is updated");
		return actualUsername.equals(expectedUsername);
	}
	
	//clicks on post, enters the text message and shares
	public void postAMessage(String message) {
		try {
		this.post.click();
		driver.switchTo().frame(postWindowIframe);
		this.messageField.click();
		waitUtils.waitForElementToBeClickable(driver, this.messageField, 25);
		this.messageField.sendKeys(message);
		driver.switchTo().defaultContent();
		this.sharePost.click();
		}catch (Exception e) {
			System.out.println("Exception occured while posting a message: " + e.getMessage());
		}
	}
	
	//Validates the message posted in the feed
	public boolean isPostShared(String expectedMessage) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		WebElement sharedPost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/p[contains(text(), '" + expectedMessage + "')]")));
		WebElement sharedPost = waitUtils.visibilityOfElementLocated(driver, By.xpath("//span/p[contains(text(), '" + expectedMessage + "')]"));
		String actualPost = sharedPost.getText();
		System.out.println("Post is Shared");
		return actualPost.equals(expectedMessage);
	}
	
	//Click on file link, send the file path and share the file
	public void shareAFile(String filepath) {
		this.file.click();
		this.uploadfileFromComputer.click();
		this.chooseFile.sendKeys(filepath);
		this.sharefile.click();
	}
	
	
	//Validate the shared file on the feed
	public boolean isFileShared(String expectedfilename) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		WebElement fileInFeed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '" + expectedfilename +"')]")));
		WebElement fileInFeed = waitUtils.visibilityOfElementLocated(driver, By.xpath("//span[contains(text(), '" + expectedfilename +"')]"));
		String actualfilename = fileInFeed.getText();
		System.out.println("File is uploaded");
		return actualfilename.equals(expectedfilename);
	}
	
	//Click on add photo and switch to iframe and save the photo
	public void addProfilePhoto(String photoPath) {
		ActionUtils.mouseHover(driver, this.addPhoto);
		driver.switchTo().frame(addPhotoIframe);
		waitUtils.waitForElementToBeVisible(driver, this.choosePhoto, 20);
		this.choosePhoto.sendKeys(photoPath);
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
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		WebElement photoUploaded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"profileImage chatter-avatarFull chatter-avatar\"]")));
		WebElement photoUploaded = waitUtils.visibilityOfElementLocated(driver, By.xpath("//span[@class=\"profileImage chatter-avatarFull chatter-avatar\"]"));
		System.out.println("Photo is uploaded");
		return photoUploaded.isDisplayed();
		}
	

}
