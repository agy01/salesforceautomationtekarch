package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.DeveloperConsole;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.MySettingsPage;

public class BaseTest {
	
	protected WebDriver driver = null;
	protected LoginPage lp = null;
	protected HomePage hp = null;
	protected MyProfilePage myProfile = null;
	protected MySettingsPage mySettings = null;
	protected DeveloperConsole devConsole = null;
		
	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		myProfile = new MyProfilePage(driver);
		mySettings = new MySettingsPage(driver);
		devConsole = new DeveloperConsole(driver);
	}
	
	@BeforeMethod
	public void windowMaximize() {
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
