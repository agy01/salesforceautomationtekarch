package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC28_MyContacts_ContactsTab {

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
		
		Select ViewMenu = new Select(driver.findElement(By.id("fcf")));
		ViewMenu.selectByVisibleText("My Contacts");
		WebElement GoButton = driver.findElement(By.xpath("//input[@value=\" Go! \"]"));
		GoButton.click();
		
		Thread.sleep(2000);
		ActionUtils.titleMatch(driver, "Contacts ~ Salesforce - Developer Edition", "MyContacts Page is displayed", "Mycontacts Page was not displayed");
		driver.quit();
		System.out.println("TC28_MyContacts_ContactsTab testing is completed");
	}

}
