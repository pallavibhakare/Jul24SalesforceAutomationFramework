package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CommonActionUtils {

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
}
