package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC33_UserName_HomeTab {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage mainHome = new HomePage(driver);
				
		//launching the application
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		//Click and validate Home tab
		Thread.sleep(2000);
		mainHome.HomeTab.click();
		
		WebElement userNameLink = driver.findElement(By.linkText("Anusha Yuvaraju"));
		userNameLink.click();
		
		ActionUtils.titleMatch(driver, "User: Anusha Yuvaraju ~ Salesforce - Developer Edition", "User Profile page is displayed", "UserProfile page was not displayed");
		
		driver.quit();
		System.out.println("TC33_UserName_HomeTab testing is completed");

	}

}
