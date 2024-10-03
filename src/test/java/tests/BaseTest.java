package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import utils.FileUtils;
import utils.ReportManager;

public class BaseTest {

	public static WebDriver driver=null;
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static Logger logger = LogManager.getLogger("BaseTest");
	
	public void setDriver(String browserName, boolean headless) {
		WebDriver driver = getBrowser(browserName, headless);
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
	@BeforeSuite(alwaysRun = true)
	public void getReport() {		
		extent = ReportManager.getInstance();
	    if (extent == null) {
	        logger.error("Failed to initialize ExtentReports instance.");
	    } else {
	        logger.info("ExtentReports instance initialized successfully.");
	    }
	}
	@AfterSuite(alwaysRun = true)
	public void writeReport() {
		
		 extent.flush();
	}
		
	@Parameters({"bName", "headless"})
	@BeforeMethod(alwaysRun = true)
	public void setup(@Optional("chrome") String browserName, @Optional("false") boolean headLess, Method mName) throws FileNotFoundException, IOException {

        test.set(extent.createTest(mName.getName()));
        
		setDriver(browserName, headLess);
		driver = getDriver();
		driver.navigate().to(FileUtils.readLoginPropertiesFile("url"));		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		 getDriver().quit();
		
	}
	
}
