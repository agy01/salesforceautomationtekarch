package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import pages.HomePage;
import pages.MyProfilePage;
import utils.ActionUtils;
import utils.switchTo;

public class TC34_EditProfileName_HomeTab {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage mainHome = new HomePage(driver);
		MyProfilePage profilePage = new MyProfilePage(driver);
				
		//launching the application
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		//Click and validate Home tab
		Thread.sleep(1000);
		mainHome.HomeTab.click();
		
		WebElement userNameLink = driver.findElement(By.xpath("//a[contains(text(), \"Anusha\")]"));
		userNameLink.click();
		
		ActionUtils.titleMatch(driver, "User: Anusha Yuvaraju ~ Salesforce - Developer Edition", "User Profile page is displayed", "UserProfile page was not displayed");
		
		profilePage.EditProfile.click();
		
		if(profilePage.PopUp.isDisplayed()) {
			System.out.println("Edit profile pop up window is displayed with contact and About tabs to edit.");
		}
		
		Thread.sleep(1000);
		String editPage = driver.getWindowHandle();
		driver.switchTo().frame("contactInfoContentId");
		profilePage.About.click();
		profilePage.lastname.clear();
		profilePage.lastname.sendKeys("Abcd");
		profilePage.saveAllButton.click();
				
		System.out.println("UserName is updated");
	
		driver.quit();
		
		System.out.println("TC34_EditProfileName_HomeTab testing Completed");

	}

}
