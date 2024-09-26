package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pages.HomePage;
import pages.LoginPage;

import java.lang.reflect.Method;

public class BaseTest {
	
//	protected LoginPage lp;
//	protected HomePage hp;
	
	
	private static ExtentReports extent;

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>(); 
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	public static Logger logger = LogManager.getLogger("BaseTest");
	
	private static synchronized void initExtentReports() {
        if (extent == null) {
            extent = ReportManager.getInstance();
        }
    }
	
	public void setDriver(String browserName, boolean headless) {
		WebDriver driver = getBrowser(browserName, headless);
		threadLocalDriver.set(driver);		
	}
	
	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	public WebDriver getBrowser(String browserName, boolean headless) {
		WebDriver driver = null;
		String browser = browserName.toLowerCase();
		switch (browser) {
		case "chrome":
			if (headless) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			} else {
				driver = new ChromeDriver();
			}
			break;

		case "firefox":
			if (headless) {
				FirefoxOptions co = new FirefoxOptions();
				co.addArguments("--headless");
				driver = new FirefoxDriver(co);
			} else {
				driver = new FirefoxDriver();
			}
			break;

		case "edge":
			if (headless) {
				EdgeOptions co = new EdgeOptions();
				co.addArguments("--headless");
				driver = new EdgeDriver(co);
			} else {
				driver = new EdgeDriver();
			}
			break;

		default:
			driver = null;
			break;
		}
		return driver;
	}


	@BeforeSuite
	public void setupSuite() {
		initExtentReports();
	}
	
	@AfterSuite
	public void teardownfinal() {
		extent.flush();
	}
	
	@Parameters({"bName"})
	@BeforeMethod()
	public void setup(@Optional("chrome") String browsername, Method name) {
		test.set(extent.createTest(name.getName()));		
		setDriver(browsername, false);
		WebDriver driver = getDriver();
		driver.manage().window().maximize();
//		lp = new LoginPage(driver);
//		hp = new HomePage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		try {
			if(getDriver()!=null) {
				getDriver().quit();
			}
		}catch(Exception e) {
			logger.error("Error during driver teardown: ", e);
		}finally {
			threadLocalDriver.remove();
		}
	}
	
}
