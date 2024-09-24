package constants;

import utils.ScreenshotUtils;

public class FileConstants {
	
	public static String ROOT_PATH = System.getProperty("user.dir");
	
	public static final String LOGIN_TEST_DATA_FILE_PATH = ROOT_PATH +
			"/src/test/java/testData/login_test_data.properties";
	
	public static final String HOMEPAGE_TEST_DATA_FILE_PATH = ROOT_PATH +
			"/src/test/java/testData/homepage_test_data.properties";
	
	public static final String MYPROFILE_TEST_DATA_FILE_PATH = ROOT_PATH +
			"/src/test/java/testData/myprofilepage_test_data.properties";
	public static final String MYSETTINGS_TEST_DATA_FILE_PATH = ROOT_PATH +
			"/src/test/java/testData/mysettings_test_data.properties";
	public static final String ACCOUNTPAGE_TEST_DATA_FILE_PATH = ROOT_PATH +
			"/src/test/java/testData/accountsPage_test_data.properties";
	public static final String LEADS_TEST_DATA_FILE_PATH = ROOT_PATH +
			"/src/test/java/testData/leads_test_data.properties";
	public static final String CREATEOPTY_TEST_DATA_FILE_PATH = ROOT_PATH +
			"/src/test/java/testData/create_opty_test_data.properties";
	public static final String CONTACTS_TEST_DATA_FILE_PATH = ROOT_PATH +
			"/src/test/java/testData/contacts_test_data.properties";
	public static final String RANDOMSCENARIOS_TEST_DATA_FILE_PATH = ROOT_PATH +
			"/src/test/java/testData/random_scenarios_test_data.properties";
	
	public static final String TEST_FILE_UPLOAD_PATH = ROOT_PATH +
			"/src/test/resources/SampleData.xlsx";
	public static final String TEST_FILE_PHOTO_PATH =ROOT_PATH +
			"/src/test/resources/photoSample.jpg";
	public static final String SCREENSHOT_FOLDER_PATH = ROOT_PATH +
			"/src/test/resources/reports/SS"+ScreenshotUtils.getTimeStamp()+".png";
	public static final String REPORTS_FILE_PATH = ROOT_PATH +
			"/src/test/resources/reports/Report"+ScreenshotUtils.getTimeStamp()+".html";
	
}
