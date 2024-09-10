package utils;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class switchTo {
	
	public static void switchToIframe(WebDriver driver, By locator) {
		WebElement iframe = driver.findElement(locator);
		driver.switchTo().frame(iframe);
	}
	
	public static void switchToAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
		System.out.println("Alert is accepted by clicking OK");
	}
	
}
