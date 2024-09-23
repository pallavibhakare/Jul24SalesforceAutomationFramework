package dataProviders;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//data provider for LoginTest.java loginToSalesfoceAccounts() method
	@DataProvider(name="Login-credentials")
	public Object loginTestData() {
		return new Object[][] {{"admin@qa.com", "admin123"}, {"palla@qa.com", "admin123"},{"pallavi@qa.com", "admin123"}};				
	}
	
	@DataProvider(name="AccountNames")
	public String[] accountNameTest() {
		return new String[] {"admin@qa.com", "admin123"};				
	}
}
