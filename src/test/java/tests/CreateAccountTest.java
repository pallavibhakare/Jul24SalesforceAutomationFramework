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
	
	@BeforeMethod(alwaysRun = true)
	public void login() throws FileNotFoundException, IOException {
		try {
	        WebDriver driver = getDriver();    
	        LoginPage lp  = new LoginPage(driver);
	        hp = lp.loginToApp(driver);        
	        hp = new HomePage(driver);
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the stack trace for troubleshooting
	        Assert.fail("Setup failed due to exception: " + e.getMessage());
	    }
	}
	
	@Test()
	public void createNewViewTC11() throws FileNotFoundException, IOException {
		
		WebDriver driver= null;
		CreateAccountPage  cap ;
		try {
			driver = getDriver();	
			cap = new CreateAccountPage(driver);
			cap.clickAccountsLink(driver);
			Assert.assertTrue(cap.isAccountsPage(driver), "Accounts Page should be displayed.");
			test.get().log(Status.INFO, "Accounts Page is displayed.");
			String name =cap.createNewView(driver);
			Assert.assertTrue(cap.isNewViewAddedInDropdown(driver, name), "Newly added view should be displayed in the account view list.");		
		}catch (Exception e) {
			e.printStackTrace();
		}
//		finally {
//			if(driver!=null) {
//			cap = new CreateAccountPage(driver);			
//			cap.deleteNewViewAccount(driver);
//			}
//		}
	}
	
	@Test
	public void editViewTC12() throws FileNotFoundException, IOException {
		
			WebDriver driver = getDriver();		
			CreateAccountPage cap = new CreateAccountPage(driver);
			logger.info("CreateAccountTest: editViewTC12:Browser instance launched");
		
			cap.clickAccountsLink(driver);
	        Assert.assertTrue(cap.isAccountsPage(driver), "Accounts Page should be displayed.");
	        test.get().log(Status.INFO, "Accounts Page is displayed.");
			
			cap.selectViewName(driver);
			Assert.assertTrue(cap.isEditViewPage(driver));
			test.get().info("Edit View page is displayed.");
			cap.editView(driver);
			Assert.assertTrue(cap.validateEditAccount(driver));
			test.get().info("View Page with new view Name in the drop down and last activity column added.");
			cap.editViewRemove(driver);
		
	}
	@Test
	public void mergeAccountsTC13() throws FileNotFoundException, IOException {
		WebDriver driver= null;
		CreateAccountPage  cap ;
		try {
			driver = getDriver();	
			cap = new CreateAccountPage(driver);
			logger.info("CreateAccountTest: mergeAccountsTC13 :Browser instance launched");
			cap.clickAccountsLink(driver);
	        Assert.assertTrue(cap.isAccountsPage(driver), "Accounts Page should be displayed.");
	        test.get().log(Status.INFO, "Accounts Page is displayed.");
			
			cap.mergeAccounts(driver);
			Assert.assertTrue(cap.isAccountsPage(driver));
			test.get().info("Accounts Page is displayed.");		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(driver!=null) {
			cap = new CreateAccountPage(driver);
			String ar= "ar";
			cap.createNewAccountNameWithAr(driver, ar);
			}
		}
	}
	@Test(description = "Bug")
	public void createAccountReportTC14() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		CreateAccountPage  cap = new CreateAccountPage(driver);
		logger.info("CreateAccountTest: createAccountReportTC14 :Browser instance launched");

		cap.clickAccountsLink(driver);
        Assert.assertTrue(cap.isAccountsPage(driver), "Accounts Page should be displayed.");
        test.get().log(Status.INFO, "Accounts Page is displayed.");
		
		cap.getAccountReports(driver);
		Assert.assertTrue(cap.isUnsavedReportPage(driver));
		test.get().info("Unsaved Reports page is displayed");
		cap.selectReportOptions(driver);
//		Assert.assertTrue(cap.isReportsDashboradPage(driver));
	}
}
