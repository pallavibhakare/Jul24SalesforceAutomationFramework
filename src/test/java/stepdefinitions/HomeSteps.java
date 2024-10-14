package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.HomePage;
import pages.LoginPage;
import tests.BaseTest;
import utils.FileUtils;

public class HomeSteps extends BaseTest{
	WebDriver driver;
	LoginPage lp;
	HomePage hp;
	
	@Before
	public void setup() throws FileNotFoundException, IOException {
		setDriver("chrome", false);
		driver = getDriver();			
		driver.navigate().to(FileUtils.readLoginPropertiesFile("url"));		
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
	
	@Given("I should be logged in successfully")
	public void i_should_be_logged_in_successfully() throws FileNotFoundException, IOException {
		driver = getDriver();
		lp = new LoginPage(driver);
		lp.loginToApp(driver);	 
	}

	@Given("I am on the home page")
	public void i_am_on_the_home_page() throws FileNotFoundException, IOException {
		 hp= new HomePage(driver);
	  Assert.assertTrue(hp.isHomePage(driver));
	}

	@When("I click usermenu")
	public void i_click_usermenu() {
		hp= new HomePage(driver);
	  hp.clickUserMenu(driver);
	}

	@Then("I should see usermenu options")
	public void i_should_see_usermenu_options() {
		 hp= new HomePage(driver);
	 Assert.assertTrue(hp.isUserMenuAvailable());  
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Given("step1 context")
	public void step1_context() {
	    
	}

	@When("step2 action")
	public void step2_action() {
	  
	}

	@Then("step3 outcome")
	public void step3_outcome(DataTable dataTable) {
//		Map<String, String> m = dataTable.asMap();
		List<String> al = dataTable.asList();
	   for(String s: al) {
		   System.out.println("Hello "+s);
	   }
	}
}
