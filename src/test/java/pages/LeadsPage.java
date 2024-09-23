package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.FileUtils;
import utils.WaitUtils;

public class LeadsPage extends BasePage {
	public LeadsPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(xpath = "//li[@id='Lead_Tab']/a")
	public WebElement leads_Tab;
	
	public void clickLeadsTabLink() {		
		leads_Tab.click();
		logger.info("'Leads' tab is clicked");
	}
	public boolean isLeadsHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual =driver.getTitle();
		String expected= FileUtils.readLeadsPagePropertiesFile("leadsHomePageTitle");
		return actual.equals(expected);
	}
}
