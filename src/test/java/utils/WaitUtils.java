package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	
	public static void waitForTitleToBe(WebDriver driver, String elementToWait) throws FileNotFoundException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.titleIs(elementToWait));
	}
	
	public static boolean explicitlyWaitForClickableElement(WebDriver driver, WebElement elementToClick) {
		boolean isElementClickable = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementToClick));
			isElementClickable = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isElementClickable;
	}
	public static void explicitWaitForElementsVisibility(WebDriver driver, WebElement elementToWait) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(elementToWait));
	}
	public static void explicitWaitForElementsInVisibility(WebDriver driver, WebElement elementToWait) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOf(elementToWait));
	}
	public static boolean explicitWaitForInVisibility(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		boolean isElementInVisible = false;
		try {
			wait.until(ExpectedConditions.invisibilityOf(element));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return isElementInVisible;
	}
	
	public static void fluentlyWaitForLoginPageTitle(WebDriver driver, String elementToWait) {
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
									.withTimeout(Duration.ofSeconds(30))
									.pollingEvery(Duration.ofMillis(2000));
	 fwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_form")));
	}
	
//	public static void fluentlyWait(WebDriver driver, WebElement elementToWait) {
//		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
//								.withTimeout(Duration.ofSeconds(30))
//								.pollingEvery(Duration.ofMillis(2000));
//		WebElement ele = fwait.until(new Function<WebDriver, WebElement>() {
//			public WebElement apply(WebDriver driver) {
//				return elementToWait;
//			}
//		});		
//	}
	
}
