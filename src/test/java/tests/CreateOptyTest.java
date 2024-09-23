package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.CreateAccountPage;
import pages.CreateOptyPage;
import pages.HomePage;
import pages.LeadsPage;
import pages.LoginPage;

public class CreateOptyTest extends BaseTest {
	HomePage hp;
	CreateAccountPage cap;
	LeadsPage ldp;
	CreateOptyPage cop;
	
	@BeforeMethod
	public void login() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		LoginPage lp  = new LoginPage(driver);
		hp = lp.loginToApp(driver);		
		test.get().log(Status.INFO, "Logged into the Application.");
		hp = new HomePage(driver);
		cop = new CreateOptyPage(driver);
		Assert.assertTrue(hp.isHomePage(driver), "Verify home page is displayed.");
		test.get().log(Status.INFO, "Home page id displayed.");		
	}
	
	@Test
	public void optyDropDownTC15() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cop.clickOptyTabLink();
		Assert.assertTrue(cop.isOptyHomePage(getDriver()));
		test.get().info(" '"+driver.getTitle()+"' home page is displayed.");
		Assert.assertTrue(cop.isOptyDropDownPresent(driver));
		test.get().info("Drop down with "+cop.getOptyDropdownOptionText());
	}
	
	@Test
	public void testOptyPipelineReportTC16() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cop.clickOptyTabLink();
		Assert.assertTrue(cop.isOptyHomePage(getDriver()));
		test.get().info(" '"+driver.getTitle()+"' home page is displayed.");
		Assert.assertTrue(cop.isNewlyCreatedOptyPage(driver));
		test.get().info("New opportunity page is displayed with opportunity details.");
	}
	
	@Test
	public void testOptyPipelineReportTC17() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cop.clickOptyTabLink();
		Assert.assertTrue(cop.isOptyHomePage(getDriver()));
		test.get().info(" '"+driver.getTitle()+"' home page is displayed.");
		boolean res = cop.clickOptyPipeline(driver);
		Assert.assertTrue(res);
		test.get().info("Report page with the opportunities that are pipelined are displayed.");
	}
	
	@Test
	public void testStuckOptyReportTC18() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cop.clickOptyTabLink();
		Assert.assertTrue(cop.isOptyHomePage(getDriver()));
		test.get().info(" '"+driver.getTitle()+"' home page is displayed.");
		boolean res = cop.clickStuckOptyLink(driver);
		Assert.assertTrue(res);
		test.get().info(driver.getTitle()+" is displayed.");
	}
	
	@Test
	public void quarterlySummaryReportTC19() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cop.clickOptyTabLink();
		Assert.assertTrue(cop.isOptyHomePage(getDriver()));
		test.get().info(" '"+driver.getTitle()+"' home page is displayed.");
		boolean res = cop.quarterlySummery(driver);
		Assert.assertTrue(res);
		test.get().info(driver.getTitle()+" is displayed.");
	}
}
