package tests;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC32_SaveNewInNewButton_ContactsTab {

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
		
		//Click and validate Contacts tab
		Thread.sleep(2000);
		mainHome.ContactsTab.click();
		
		ActionUtils.titleMatch(driver, "Contacts: Home ~ Salesforce - Developer Edition", "Contacts Home page is dispalyed", "Contacts Home page was not displayed");
		
		//Click and validate New Button on Contacts Tab
		WebElement NewButton = driver.findElement(By.xpath("//td[@class=\"pbButton\"]/child::input"));
		NewButton.click();
		
		ActionUtils.titleMatch(driver, "Contact Edit: New Contact ~ Salesforce - Developer Edition", "Contacts Edit page is displayed", "Contacts Edit page was not displayed");
		
		//Input lastName
		WebElement LastName = driver.findElement(By.id("name_lastcon2"));
		LastName.sendKeys("Indian");

		//Lookup Window Handle and Choose Global Media
		WebElement AccountNameLookUp = driver.findElement(By.className("lookupIcon"));
		AccountNameLookUp.click();
		String MainWindow = driver.getWindowHandle();
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		driver.switchTo().window(getWindow[1]);
		Thread.sleep(3000);
		driver.switchTo().frame("searchFrame");
		driver.findElement(By.id("lksrch")).sendKeys("Global");
		driver.findElement(By.xpath("//input[@name='go']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("resultsFrame");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//tr[@class=\"dataRow even last first\"]/th/a")).click();
		driver.switchTo().window(MainWindow);
		Thread.sleep(2000);
		
		//Click and Validate save & New Button
		WebElement saveAndNewButton = driver.findElement(By.xpath("//td[@id=\"topButtonRow\"]/child::input[2]"));
		saveAndNewButton.click();
		
		ActionUtils.titleMatch(driver, "Contact Edit: New Contact ~ Salesforce - Developer Edition", "Save & New Option worked", "Save & New option did not work");
		
		driver.quit();
		System.out.println("TC32_SaveAndNewInNewButton_ContactsTab testing is completed");

	}

}
