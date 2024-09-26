package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitUtils {
	
	// Method to apply an explicit wait for a WebElement to become visible
		public static void ExplicityWait(WebDriver driver, WebElement elementToWait) {
			// Create a WebDriverWait object with a timeout of 30 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
			// Wait until the specified WebElement is visible on the page
			wait.until(ExpectedConditions.visibilityOf(elementToWait));
		}
		
		public static void ExplicityWaitforElement(WebDriver driver, By elementToWait) {
			// Create a WebDriverWait object with a timeout of 30 seconds
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
			// Wait until the specified WebElement is visible on the page
			wait.until(ExpectedConditions.presenceOfElementLocated(elementToWait));
		}
		
		//Method used to wait for Element to be visible
		public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeoutInSec) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSec));
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		//Method used to wait for Title to be visible
		public static void waitForTitle(WebDriver driver, String element, int timeoutInSec) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSec));
			wait.until(ExpectedConditions.titleIs(element));
			
		}
		
		//Method used to wait for the element to be clickable
		public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutInSec) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSec));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		
		public static WebElement visibilityOfElementLocated(WebDriver driver, By locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public static void waitForPageToLoad(WebDriver driver, int timeOutInSec) {
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOutInSec));
		}
}
