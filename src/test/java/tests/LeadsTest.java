package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import listeners.TestListener;
import pages.ContactsPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.LeadsPage;
import pages.LoginPage;

@Listeners(TestListener.class)
public class LeadsTest extends BaseTest {
	LoginPage lp;
	HomePage hp;
	CreateAccountPage cap;
	LeadsPage ldp;
	ContactsPage cp;
	static WebDriver driver = null;
	@BeforeMethod(alwaysRun = true)
	public void login() throws FileNotFoundException, IOException {
		driver = getDriver();	
		lp  = new LoginPage(driver);
		hp = lp.loginToApp(driver);		
		hp = new HomePage(driver);
		ldp= new LeadsPage(driver);
		cp= new ContactsPage(driver);
	}
	@AfterMethod(alwaysRun = true)
	public void logoutFinal() throws FileNotFoundException, IOException {
		driver = getDriver();
		 hp.logout(driver);
	}
	
	@Test
	public void leadsTabTC20() throws FileNotFoundException, IOException {
		driver = getDriver();	
		logger.info("LeadsTest: leadsTabTC20:Browser instance launched");	
		ldp.clickLeadsTabLink(driver);
		Assert.assertTrue(ldp.isLeadsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
	}
	
	@Test
	public void leadsSelectViewTC21() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		logger.info("LeadsTest: leadsSelectViewTC21:Browser instance launched");	
		ldp.clickLeadsTabLink(driver);
		Assert.assertTrue(ldp.isLeadsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");		
		Assert.assertTrue(ldp.isListViewOptionsAvailable(driver));
		test.get().log(Status.INFO, ldp.getListViewOptions(driver)+" is displayed.");
	}
	
	@Test
	public void defaultViewTC22() throws FileNotFoundException, IOException {
		 driver = getDriver();	
		logger.info("LeadsTest: defaultViewTC22:Browser instance launched");	
		ldp.clickLeadsTabLink(driver);
		Assert.assertTrue(ldp.isLeadsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");	
		ldp.viewOptionsSelect(driver);
		Assert.assertTrue(ldp.viewOptionsSelect(driver));
		test.get().log(Status.INFO, "Same as last option selected.");		
	}
	@Test
	public void todaysLeadsTC23() throws FileNotFoundException, IOException {
		 driver = getDriver();	
		logger.info("LeadsTest: todaysLeadsTC23:Browser instance launched");	
		ldp.clickLeadsTabLink(driver);
		Assert.assertTrue(ldp.isLeadsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");	
		Assert.assertTrue(ldp.isTodaysLeadsPage(driver));
		test.get().log(Status.INFO, "Today's Lead is displayed.");			
	}
	
	@Test
	public void newLeadsTC24() throws FileNotFoundException, IOException {
		WebDriver driver= null;
		try {
			driver = getDriver();
			logger.info("LeadsTest: newLeadsTC24:Browser instance launched");
			ldp.clickLeadsTabLink(driver);
			Assert.assertTrue(ldp.isLeadsHomePage(driver));
			test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
			ldp.clikcNewLeads(driver);
			Assert.assertTrue(ldp.isNewLeadCreationPage(driver));
			test.get().log(Status.INFO, "'"+driver.getTitle()+"' page is displayed.");
			boolean res= ldp.enterNewLeadsDetails(driver);
			Assert.assertTrue(res);
			test.get().log(Status.INFO, "New lead is saved and "+driver.getTitle()+" is opened.");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(driver!=null) {
			cp.deleteRecord(driver);
			}
		}
		}
}
