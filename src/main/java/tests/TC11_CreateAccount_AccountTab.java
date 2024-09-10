package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.AccountsTabPage;
import pages.LoginPage;
import pages.HomePage;

public class TC11_CreateAccount_AccountTab {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage mainHome = new HomePage(driver);
		AccountsTabPage accounts = new AccountsTabPage(driver);
		
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		
		Thread.sleep(2000);
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("User Logged in successfully");
		}else {
			System.out.println("User was not able to login");
		}
		
		mainHome.AccountsTab.click();
		Thread.sleep(2000);
		accounts.NewButton.click();
		
		String actualTitle1 = driver.getTitle();
		String expectedTitle1 = "Account Edit: New Account ~ Salesforce - Developer Edition";
		
		if(actualTitle1.equals(expectedTitle1)) {
			System.out.println("Accounts Page is displayed");
		}else {
			System.out.println("Accounts page not displayed");
		}
			
		WebElement AccountName = driver.findElement(By.id("acc2"));
		AccountName.sendKeys("Testing Framework");
		WebElement saveButton = driver.findElement(By.xpath("//td[@id=\"topButtonRow\"]/child::input[1]"));
		saveButton.click();
		
		String actualTitle2 = driver.getTitle();
		String expectedTitle2 = "Account: Testing Framework ~ Salesforce - Developer Edition";
		
		if(actualTitle2.equals(expectedTitle2)) {
			System.out.println("New Account is created");
		}else {
			System.out.println("New account was not able to create");
		}
		
		
		driver.quit();
			

	}

}
