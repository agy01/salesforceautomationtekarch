package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC24_NewLead_LeadsTab {

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
		mainHome.LeadsTab.click();
		
		ActionUtils.titleMatch(driver, "Leads: Home ~ Salesforce - Developer Edition", "Leads Home page is displayed", "Leads Home page is not displayed");
		
		WebElement NewButton = driver.findElement(By.xpath("//td[@class=\"pbButton\"]/child::input"));
		NewButton.click();
		
		WebElement LastName = driver.findElement(By.id("name_lastlea2"));
		LastName.sendKeys("ABCD");
		WebElement CompanyName = driver.findElement(By.id("lea3"));
		CompanyName.sendKeys("ABCD");
		
		WebElement saveButton = driver.findElement(By.xpath("//td[@class=\"pbButton\"]/child::input[1]"));
		saveButton.click();
		
		ActionUtils.titleMatch(driver, "Lead: ABCD ~ Salesforce - Developer Edition", "New Lead is created", "New lead was not craeted");
		
		driver.quit();
		System.out.println("TC24_NewLead_LeadsTab testing is completed");

	}

}
