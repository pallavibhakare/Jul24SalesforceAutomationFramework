package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTest {
	@Test
	public void testMethod(){
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://login.salesforce.com");
		
	}
}
