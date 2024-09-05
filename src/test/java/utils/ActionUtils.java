package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtils {

	public static void mouseHover(WebDriver driver, WebElement elementToHover) {
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).build().perform();
	}
}
