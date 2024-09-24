package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import listeners.TestListener;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.LoginPage;

@Listeners(TestListener.class)
public class CreateAccountTest extends BaseTest {
	HomePage hp;
	CreateAccountPage cap;
	
	@BeforeMethod
	public void login() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		LoginPage lp  = new LoginPage(driver);
		hp = lp.loginToApp(driver);		
		test.get().log(Status.INFO, "Logged into the Application.");
		hp = new HomePage(driver);
		cap = new CreateAccountPage(driver);
		Assert.assertTrue(hp.isHomePage(driver), "Verify home page is displayed.");
		test.get().log(Status.INFO, "Home page is displayed.");
		cap.clickAccountsLink(driver);
		Assert.assertTrue(cap.isAccountsPage(driver), "Accounts Page should be displayed.");
		test.get().log(Status.INFO, "Accounts Page is displayed.");
	}
	
	@Test()
	public void createNewViewTC11() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cap.createNewView(driver);
		Assert.assertTrue(cap.isNewViewAddedInDropdown(driver), "Newly added view should be displayed in the account view list.");
	}
	
	@Test
	public void editViewTC12() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		cap.selectViewName(driver);
		Assert.assertTrue(cap.isEditViewPage(driver));
		test.get().info("Edit View page is displayed.");
		cap.editView(driver);
		Assert.assertTrue(cap.validateEditAccount(driver));
		test.get().info("View Page with new view Name in the drop down and last activity column added.");
	}
	@Test
	public void mergeAccountsTC13() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		cap.mergeAccounts(driver);
		Assert.assertTrue(cap.isAccountsPage(driver));
		test.get().info("Accounts Page is displayed.");		
	}
	@Test(description = "Bug")
	public void createAccountReportTC14() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		cap.getAccountReports(driver);
		Assert.assertTrue(cap.isUnsavedReportPage(driver));
		test.get().info("Unsaved Reports page is displayed");
		cap.selectReportOptions(driver);
		Assert.assertTrue(cap.isReportsDashboradPage(driver));
	}
}
