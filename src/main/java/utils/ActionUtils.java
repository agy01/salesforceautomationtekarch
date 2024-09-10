package utils;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ActionUtils {
	
	//Method will Return the attribute value for the specified element
	public static String getElementAttribute(WebElement element) {
		return element.getAttribute("value");
	}
	
	//Method will Move the mouse to element and perform click operation
	public static void mouseHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		element.click();

	}
	
	//Method from HomePage dropdownElement iterates and selects the specified value
	public static void selectDropDownValue(WebDriver driver, List<WebElement> dropDownElement, String value) {
		for(WebElement option : dropDownElement) {
			if(option.getText().equalsIgnoreCase(value)) {
				option.click();
				break;
			}
		}
	}
	
	//Method will Validates with each expected option to actual option
	public static boolean validateDropDownOptions(List<WebElement> actualOptions, List<String> expectedOptions) {
		for(String expectedOption : expectedOptions) {
			boolean isOptionPresent = false;
			for(WebElement actualOption : actualOptions) {
				if(actualOption.getText().equalsIgnoreCase(expectedOption)) {
					isOptionPresent = true;
					break;
				}
			}
			if(!isOptionPresent) {
				return false; // If any option is missing, return false
			}
		}
		return true; // If all expected options are present, return true
		
	}
	
	// Method to check if an element is already present in the list
	public static boolean isElementPresentInList(WebElement listElement, String value) {
		Select select = new Select(listElement);
		List<WebElement> allOptions = select.getOptions();
		
		for(WebElement option : allOptions) {
			if(option.getText().equalsIgnoreCase(value)) {
				return true;				
			}
		}
		return false;
	}
	
	// Method to select an element from a list (like Available Tabs)
	public static boolean selectElementInList(WebElement listElement, String value) {
		Select select = new Select(listElement);
		List<WebElement> alloptions = select.getOptions();
		
		for(WebElement option : alloptions) {
			if(option.getText().equals(value)) {
				option.click();
				return true;				
			}
		}
		return false;
	}
	
	//Method that handles the switch window
	public static void switchToPopUpWindow(WebDriver driver) {
		String mainWindowHandle = driver.getWindowHandle();  // Store the main window handle
        Set<String> allWindowHandles = driver.getWindowHandles();  // Get all window handles

        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);  // Switch to the new window
                break;
            }
        }
	}
	
	//Method that handle switch back to main window
	public static void switchBackToMainWindow(WebDriver driver) {
		String mainWindowHandle = driver.getWindowHandle();
		driver.close();
		Set<String> allWindowHandles = driver.getWindowHandles();
	    for (String handle : allWindowHandles) {
	        if (!handle.equals(mainWindowHandle)) {
	            driver.switchTo().window(handle);  // Switch to the remaining window
	            break;
	        }
	    }
    }
}
