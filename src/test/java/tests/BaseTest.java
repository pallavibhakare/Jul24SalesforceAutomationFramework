package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.HomePage;
import pages.LoginPage;
import utils.FileUtils;

public class BaseTest {

	WebDriver driver = null;
	LoginPage lp = null;
	HomePage hp = null;
	
	public void getAppURL() throws FileNotFoundException, IOException {
		driver.navigate().to(FileUtils.readLoginPropertiesFile("url"));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setup() throws FileNotFoundException, IOException {
		driver = new ChromeDriver();
		getAppURL();		
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
//		driver.close();
	}
	
}
