package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC31_CancelInCreateNewView_ContactsTab {

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
		
		//Click and validate Leads tab
		Thread.sleep(2000);
		mainHome.ContactsTab.click();
		
		ActionUtils.titleMatch(driver, "Contacts: Home ~ Salesforce - Developer Edition", "Contacts Home page is dispalyed", "Contacts Home page was not displayed");
		
		WebElement createNewView = driver.findElement(By.linkText("Create New View"));
		createNewView.click();
		
		WebElement ViewName = driver.findElement(By.id("fname"));
		ViewName.clear();
		ViewName.sendKeys("ABCD");
		
		WebElement ViewUniqueName = driver.findElement(By.id("devname"));
		ViewUniqueName.clear();
		ViewUniqueName.sendKeys("EFGH");

		WebElement CancelButton = driver.findElement(By.xpath("//input[@data-uidsfdc=\"3\"]/following-sibling::input"));
		CancelButton.click();
		
		ActionUtils.titleMatch(driver, "Contacts: Home ~ Salesforce - Developer Edition", "Contact new view was cancelled", "Contact new view was created");
		
		driver.quit();
		
		System.out.println("TC31_CancelInCreateNewView_ContactsTab testing is completed");

	}

}
