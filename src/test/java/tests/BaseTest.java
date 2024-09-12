package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.FileUtils;

public class BaseTest {

	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	public void setDriver(String browserName, boolean headless) {
		WebDriver driver = getBrowser(browserName, false);
		threadLocalDriver.set(driver);
	}
	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
	public WebDriver getBrowser(String browserName, boolean headless) {
		WebDriver driver = null;
		browserName = browserName.toLowerCase();
		switch (browserName) {
		case "chrome":
			if(headless) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			}else {
				driver = new ChromeDriver();
			}
			break;
		case "firefox":
			if(headless) {
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);
			}else {
				driver = new FirefoxDriver();
			}
			
			break;
		case "edge":
			if(headless) {
				EdgeOptions eo = new EdgeOptions();
				eo.addArguments("--headless");
				driver = new EdgeDriver(eo);
			}else {
				driver = new EdgeDriver();
			}
			break;
		case "safari":
			
			//Safari does not support headless mode 
			driver = new SafariDriver();
			break;
		default:
			driver = null;
			break;
		}
		return driver;
	}
		
	@BeforeMethod(alwaysRun = true)
	public void setup() throws FileNotFoundException, IOException {

		setDriver("chrome", false);
		WebDriver driver = getDriver();
		driver.navigate().to(FileUtils.readLoginPropertiesFile("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		getDriver().quit();
	}
	
}
