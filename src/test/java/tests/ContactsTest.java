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
import utils.FileUtils;
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
		WebDriver driver= null;
		try {
		 driver = getDriver();	
		cp.clickContactsTabLink();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
		boolean result = cp.clickNewButton(driver);
		Assert.assertTrue(result);
		test.get().log(Status.INFO, "New contact edit page is displayed.");		
		Assert.assertTrue(cp.isNewContactHomePage(driver));
		test.get().log(Status.INFO, "New contact created.");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(driver!=null) {
			cp.deleteRecord(driver);
			}
		}
	}
	
	@Test
	public void createNewViewTC26() throws FileNotFoundException, IOException {
		WebDriver driver= null;
		try {
			driver = getDriver();	
			cp.clickContactsTabLink();
			Assert.assertTrue(cp.isContactsHomePage(driver));
			test.get().log(Status.INFO, driver.getTitle()+" is displayed.");		
			boolean result = cp.clickCreateNewViewLink(driver);
			Assert.assertTrue(result);
			test.get().log(Status.INFO, "'Create New View' page is displayed.");	
			boolean viewName = cp.isViewNameEntered(driver);
			Assert.assertTrue(viewName);
			test.get().log(Status.INFO, "View Name is entered in the view name field.");
			boolean viewUniqueName = cp.isViewUniqueNameGenerated(driver);
			Assert.assertTrue(viewUniqueName);
			test.get().log(Status.INFO, "View Unique Name is entered in the view unique name field.");
			Assert.assertTrue(cp.saveDetails(driver));
			test.get().log(Status.INFO, "Contacts home page is displayed with new view selected");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(driver!=null) {
			cp.deleteView(driver);
			}
		}
	}
	@Test
	public void checkRecentlyCreatedContactTC27() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cp.clickContactsTabLink();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
		cp.checkRecentlyCreated(driver);
		Assert.assertTrue(cp.isRecentlyCreatedPage(driver));
		test.get().log(Status.INFO, "Recently Created contacts are displayed.");
	}
	@Test
	public void checkMyContactsTC28() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cp.clickContactsTabLink();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
		cp.selectMyContacts(driver);
		Assert.assertTrue(cp.isMyContactsPage(driver));
		test.get().log(Status.INFO, "My Contacts page is displayed.");
	}
	@Test
	public void viewContactInContactPageTC29() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cp.clickContactsTabLink();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
		String name = cp.clickAContactName(driver);
		Assert.assertTrue(cp.isContactNamesPage(driver, name));
		test.get().log(Status.INFO, "Contact page for " + name + " is displayed.");
	}
	
	@Test
	public void errorForNewViewTC30() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cp.clickContactsTabLink();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
		boolean res = cp.clickCreateNewViewLink(driver);
		Assert.assertTrue(res);
		test.get().log(Status.INFO, "Create New View page is opened.");
		cp.enterUniqueViewName();
		cp.saveCreateNewView();
		Assert.assertTrue(cp.validateErrorMessage(driver));
		test.get().log(Status.INFO, "Error message is appeared under the View Name Field. The Error message appears as:'Error: You must enter a value'");
	}
	
	@Test
	public void checkingCancelButtonTC31() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cp.clickContactsTabLink();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
		boolean res = cp.clickCreateNewViewLink(driver);
		Assert.assertTrue(res);
		test.get().log(Status.INFO, "Create New View page is opened.");
		
		cp.enterViewName();
		cp.enterUniqueViewName();
		cp.cancelCreateNewView();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, "Contacts Home Page is displayed without '"+FileUtils.readContactsPropertiesFile("viewNameForCancel")+"' view name.");
	}
	@Test
	public void checkSaveAndNewButtonTC32() throws FileNotFoundException, IOException {
		WebDriver driver = null;
		try {
		driver = getDriver();	
		cp.clickContactsTabLink();
		Assert.assertTrue(cp.isContactsHomePage(driver));
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");
		
		boolean res= cp.clickNewButton(driver);
		Assert.assertTrue(res);
		test.get().log(Status.INFO, driver.getTitle()+" is displayed.");	
		Assert.assertTrue(cp.isNewContactDetailsPage(driver));
		test.get().log(Status.INFO, "New Contact is created. "+driver.getTitle()+"is displayed.");
		}catch(Exception e) {
			test.get().log(Status.INFO, "An error occurred: " + e.getMessage());
	        Assert.fail("Test failed due to an exception: " + e.getMessage());
		}finally {
			if(driver!=null) {
			cp.deleteRecord(driver);
			}
		}
	}
}
