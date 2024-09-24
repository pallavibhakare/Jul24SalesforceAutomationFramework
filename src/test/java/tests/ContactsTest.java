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
import pages.HomePage;
import pages.LoginPage;
import utils.WaitUtils;

@Listeners(TestListener.class)
public class ContactsTest extends BaseTest {

	LoginPage lp;
	HomePage hp;
	ContactsPage cp;
	
	@BeforeMethod
	public void login() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		lp  = new LoginPage(driver);
		hp = lp.loginToApp(driver);		
		test.get().log(Status.INFO, "Logged into the Application.");
		hp = new HomePage(driver);
		Assert.assertTrue(hp.isHomePage(driver), "Verify home page is displayed.");
		test.get().log(Status.INFO, "Home page is displayed.");	
		cp = new ContactsPage(driver);
	}
	@AfterMethod
	public void logout() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		hp.logout(driver);
	}
	
	@Test
	public void createNewContactTC25() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cp.clickContactsTabLink();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
		boolean result = cp.clickNewButton(driver);
		Assert.assertTrue(result);
		test.get().log(Status.INFO, "New contact edit page is displayed.");		
		Assert.assertTrue(cp.isNewContactHomePage(driver));
		test.get().log(Status.INFO, "New contact created.");
	}
	
	@Test
	public void createNewViewTC26() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cp.clickContactsTabLink();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");		
		boolean result = cp.clickCreateNewViewLink(driver);
		Assert.assertTrue(result);
		test.get().log(Status.INFO, "'Create New View' page is displayed.");	
		boolean viewName = cp.isViewNameEntered(driver);
		Assert.assertTrue(viewName);
		test.get().log(Status.INFO, "View Name is entered in the view name field.");
		boolean viewUniqueName = cp.isViewUniqueNameEntered(driver);
		Assert.assertTrue(viewUniqueName);
		test.get().log(Status.INFO, "View Unique Name is entered in the view unique name field.");
		Assert.assertTrue(cp.saveDetails(driver));
		test.get().log(Status.INFO, "Contacts home page is displayed with new view selected");
	}
	
}
