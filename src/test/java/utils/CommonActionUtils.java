package utils;


import java.util.NoSuchElementException;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class CommonActionUtils {
	private static String parentWindow;
	public static void mouseHover(WebDriver driver, WebElement elementToHover) {
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).build().perform();
	}
	public static void clickElement(WebDriver driver, WebElement elementToClick) {
		elementToClick.click();
	}
	public static String getElementValueAttribute(WebElement element) {
		return element.getAttribute("value");
	}
	public static void selectDropDownOption(WebDriver driver, WebElement dropdownElement, String optionName) {
	    try {
	        
	        // Create a Select instance for the dropdown and select by visible text
	        Select dropdownOption = new Select(dropdownElement);
	        dropdownOption.selectByVisibleText(optionName);

	        System.out.println("Successfully selected option: " + optionName);
	    } catch (NoSuchElementException e) {
	        System.err.println("Option with text '" + optionName + "' not found in the dropdown");
	    } catch (Exception e) {
	        System.err.println("Failed to select option: " + optionName + " due to " + e.getMessage());
	    }
	}

	public static String getParentWindowHandle(WebDriver driver) {		
			return driver.getWindowHandle();
	}
	
	public static String getChildWindows(WebDriver driver) {
		
		String childWindow = "";
		Set<String> allOpenWindows = driver.getWindowHandles();
		for(String window : allOpenWindows) {
			childWindow = window;
			driver.switchTo().window(childWindow);			
		}
		return childWindow;
	}
	public static void backToParentWindow(WebDriver driver) {
		driver.switchTo().window(parentWindow);
		driver.quit();
	}
}
