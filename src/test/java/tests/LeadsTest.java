package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.CreateAccountPage;
import pages.HomePage;
import pages.LeadsPage;
import pages.LoginPage;

public class LeadsTest extends BaseTest {
	HomePage hp;
	CreateAccountPage cap;
	LeadsPage ldp;
	@BeforeMethod
	public void login() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		LoginPage lp  = new LoginPage(driver);
		hp = lp.loginToApp(driver);		
		test.get().log(Status.INFO, "Logged into the Application.");
		hp = new HomePage(driver);
//		cap = new CreateAccountPage(driver);
		ldp= new LeadsPage(driver);
		Assert.assertTrue(hp.isHomePage(driver), "Verify home page is displayed.");
		test.get().log(Status.INFO, "Home page id displayed.");		
	}
	
	@Test
	public void leadsTabTC20() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		ldp.clickLeadsTabLink();
		Assert.assertTrue(ldp.isLeadsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
	}
}
