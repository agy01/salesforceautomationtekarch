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

public class TC35_RemoveTab {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage mainHome = new HomePage(driver);
		
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		//Click Plus icon
		Thread.sleep(1000);
		mainHome.PlusIcon.click();
		
		WebElement CustomizeMyTab = driver.findElement(By.className("btnImportant"));
		CustomizeMyTab.click();
		
		WebElement SelectedTab = driver.findElement(By.id("duel_select_1"));
		Select dropdownoption = new Select(SelectedTab);
		dropdownoption.selectByVisibleText("Accounts");
		ActionUtils.mouseHover(driver, driver.findElement(By.xpath("//img[@title=\"Remove\"]")));
		
		WebElement SaveButton = driver.findElement(By.xpath("//td[@id=\"bottomButtonRow\"]/child::input[1]"));
		SaveButton.click();
		
		Thread.sleep(2000);
		mainHome.UserMenuDropDown.click();
		ActionUtils.mouseHover(driver, driver.findElement(By.xpath("//a[@title=\"Logout\"]")));
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		ActionUtils.titleMatch(driver, "Home Page ~ Salesforce - Developer Edition", "User logged in successfully", "User could not loggin");
		driver.quit();
		System.out.println("TC35_RemoveTab testing completed");

	}

}
