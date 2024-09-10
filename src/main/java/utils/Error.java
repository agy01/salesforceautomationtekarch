package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Error {
	public static void ErrorMessage(WebDriver driver, WebElement element) {
		if(element.isDisplayed()) {
			String errorMessage = element.getText();
			System.out.println("Error: " + errorMessage);
		}
	}

}
