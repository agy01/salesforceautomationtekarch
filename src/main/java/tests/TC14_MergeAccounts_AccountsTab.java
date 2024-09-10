package tests;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.LoginPage;
import pages.HomePage;
import utils.switchTo;

public class TC14_MergeAccounts_AccountsTab {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage mainHome = new HomePage(driver);
		
		//launching the application
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		//validating is user logged in
		String actualTitle = driver.getTitle();
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		
		Thread.sleep(2000);
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("User Logged in successfully");
		}else {
			System.out.println("User was not able to login");
		}
		
		//Open Accounts tab and validate
				mainHome.AccountsTab.click();
				String actualTitle1 = driver.getTitle();
				System.out.println(actualTitle1);
				String expectedTitle1 = "Accounts: Home ~ Salesforce - Developer Edition";
				if(actualTitle1.equals(expectedTitle1)) {
					System.out.println("Accounts Home page is displayed");
				}else {
					System.out.println("Accounts Home page is not displayed");
				}
				
		WebElement MergeAccounts = driver.findElement(By.linkText("Merge Accounts"));
		MergeAccounts.click();
		
		WebElement FindAccount = driver.findElement(By.id("srch"));
		FindAccount.sendKeys("Test");
		
		WebElement FindAccountButton = driver.findElement(By.name("srchbutton"));
		FindAccountButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> Results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[contains(text(), \"Testing\")]")));
		
		WebElement checkbox = driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));
		
		if(!checkbox.isSelected()) {
			checkbox.click();
		}
		
		if(checkbox.isSelected()) {
			System.out.println("Selected the elements");
		}else {
			System.out.println("Elements are not selected");
		}
		
		WebElement NextButton = driver.findElement(By.xpath("//div[@class=\"pbBottomButtons\"]/child::input[1]"));
		NextButton.click();
		
		WebElement MergeButton = driver.findElement(By.xpath("//input[@value=\" Merge \"]"));
		MergeButton.click();
		
		switchTo.switchToAlert(driver);
		
		driver.quit();
		
		System.out.println("TC14_MergeAccounts_AccountTab completed");

	}

}
