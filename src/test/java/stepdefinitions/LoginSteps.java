package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.HomePage;
import pages.LoginPage;
import tests.BaseTest;
import utils.FileUtils;

public class LoginSteps extends BaseTest {
	WebDriver driver;
	LoginPage lp;
	HomePage hp;
	
	@Before
	public void setup() {
		driver = getBrowser("chrome", false);
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
	
	@Given("I launched SGDC login page")
	public void i_launched_sgdc_login_page() throws FileNotFoundException, IOException { 
//		driver = getBrowser("chrome", false);
	    lp = new LoginPage(driver);
	    driver.get(FileUtils.readLoginPropertiesFile("url"));
	}
	
	@When("I enter valid username")
	public void i_enter_valid_username() throws FileNotFoundException, IOException {
	    lp.enterUsername();	    
	}
	
	@When("enter valid password")
	public void enter_valid_password() throws FileNotFoundException, IOException {
	   lp.enterPassword();
	}
	
	@When("clicked login button")
	public void clicked_login_button() {
	    lp.clickLogin();
	}
	
	@Then("I should see Home page")
	public void i_should_see_home_page() throws FileNotFoundException, IOException {
	  hp= new HomePage(driver);
	  Assert.assertTrue(hp.isHomePage(driver));
	}

	@When("I enter invalid username")
	public void i_enter_invalid_username() throws FileNotFoundException, IOException {
	    lp.enterWrongUsername();
	}

	@When("enter invalid password")
	public void enter_invalid_password() throws FileNotFoundException, IOException {
	    lp.enterWrongPassword();
	}

	@Then("I should see Error message")
	public void i_should_see_error_message() throws FileNotFoundException, IOException {
		Assert.assertTrue(lp.isErrorMsgDisplayed());
	}

	
	@Given("I want to go to the {string}")
	public void i_want_to_go_to_the(String string) {
	  
	}
	@When("I enter {string} and {string}")
	public void i_enter_pallavi_qa_com_and_admin(String username, String pass) {
		System.out.println("Username is: "+username+"passwrod is: "+pass);
	}
	
	@Then("I click on the login button")
	public void i_click_on_the_login_button() {
	   
	}
	
	
}
