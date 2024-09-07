package pages;


import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import utils.FileUtils;
import utils.WaitUtils;


public class HomePage extends BasePage {
	
	
	
	public HomePage(WebDriver driver) {
		super(driver);	
	}

	public boolean isHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String expectedHomepageTitle = FileUtils.readHomepagePropertiesFile("homePageTitle");
		String actualHomepageTitle = driver.getTitle();
		if(actualHomepageTitle.equals(expectedHomepageTitle)) {
			return true;
		}else {
			return false;
		}
		
	}
}
