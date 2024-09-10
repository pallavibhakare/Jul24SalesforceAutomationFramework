package seleniumWithFirebaseApp;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirebaseApp {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://selenium-prd.firebaseapp.com/");
		driver.manage().window().maximize();
		
		//***** Login Page****
		driver.findElement(By.id("email_field")).sendKeys("admin123@gmail.com");
		driver.findElement(By.id("password_field")).sendKeys("admin123");
		Thread.sleep(2000);
		driver.findElement(By.tagName("button")).click();
		Thread.sleep(2000);
		//****** HOME Tab *****
//		driver.findElement(By.linkText("Home")).click();
//		driver.findElement(By.id("name")).sendKeys("John");
//		driver.findElement(By.id("lname")).sendKeys("Smith");
//		driver.findElement(By.id("postaladdress")).sendKeys("123 Main");
//		driver.findElement(By.id("personaladdress")).sendKeys("321 Main");
//		//RADIO Button 
//		driver.findElement(By.xpath("//input[@value='female' and @id='radiobut']")).click();
		
		//directly choosing value from dropdown not efficient
//		driver.findElement(By.id("city")).sendKeys("Goa");
//		WebElement dropwdown0 = driver.findElement(By.id("city"));
//		Select city = new Select(dropwdown0);
//		city.selectByValue("goa");
		
//		Thread.sleep(10000);
		
		//identify DROPDOWN element using Select class of selenium
//		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		//WebElement dropwdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("course")));
//		WebElement dropwdown1 = driver.findElement(By.id("course"));
//		Select course = new Select(dropwdown1);
//		course.selectByValue("mca");
//		Thread.sleep(10000);
//		WebElement dropdown2 = driver.findElement(By.id("district"));
//		Select district = new Select(dropdown2);
//		district.selectByIndex(4);
//		Thread.sleep(10000);
//		WebElement dropedown3 = driver.findElement(By.id("state"));
//		Select state = new Select(dropedown3);
//		state.selectByVisibleText("UP");
//		Thread.sleep(10000);
//		driver.findElement(By.id("pincode")).sendKeys("123321");
//		driver.findElement(By.id("emailid")).sendKeys("abcd@gmail.com");
//		Thread.sleep(2000);
//		driver.findElement(By.className("bootbutton")).click();
		
		//*****TAB 2 Switch-To ******
//		WebElement switchTo = driver.findElement(By.xpath("//*[@id='user_div']/div[1]/div[1]/button"));
		Actions action = new Actions(driver);
//		action.moveToElement(switchTo).build().perform();
//		driver.findElement(By.xpath("//div[@id='user_div']/div/div[1]/div/a[1]")).click();
//		driver.findElement(By.xpath("//div[@id='user_div']/div[2]/div/button[1]")).click();
//		Thread.sleep(5000);
//		driver.switchTo().alert().accept();
//		WebElement promptAlert = driver.findElement(By.xpath("//div[@id='user_div']/div[2]/div/button[2]"));
//		promptAlert.click();
//		Alert alert = driver.switchTo().alert();
//		alert.sendKeys("John");
//		alert.accept();
//		Thread.sleep(10000);
//		switchTo = driver.findElement(By.xpath("//*[@id='user_div']/div[1]/div[1]/button"));
//		action.moveToElement(switchTo).build().perform();
//		driver.findElement(By.xpath("//div[@id='user_div']/div/div/div/a[contains(text(),'Windows')]")).click();
//		Thread.sleep(5000);
		
		//***** Open New Tab ******
//		driver.findElement(By.xpath("//div[@id='user_div']/div[2]/a[1]/button[1]")).click();
//		String parentWindow = driver.getWindowHandle();
//		Set<String> childWindows = driver.getWindowHandles();
//		String[] getWindow = childWindows.toArray(new String[childWindows.size()]);
//		driver.switchTo().window(getWindow[1]);
//		System.out.println(driver.getTitle());
//		driver.close();
//		driver.switchTo().window(parentWindow);
//		System.out.println(driver.getTitle());
//		driver.findElement(By.xpath("//div[@id='user_div']/div[2]/a[2]/button")).click();
//		parentWindow = driver.getWindowHandle();
//		String childWindowId = " ";
//		Set<String> newWindows = driver.getWindowHandles();
//		for(String s : newWindows) {
//			childWindowId = s;
//			driver.switchTo().window(childWindowId);
//			
//		}
//		driver.close();
//		driver.switchTo().window(parentWindow);
//		switchTo = driver.findElement(By.xpath("//*[@id='user_div']/div[1]/div[1]/button"));
//		action.moveToElement(switchTo).build().perform();
//		driver.findElement(By.xpath("//a[contains(text(), 'Tabs')]")).click();
//		driver.findElement(By.xpath("//div[@class='tab']/button[1]")).click();
//		driver.findElement(By.xpath("//div[@class='tab']/button[2]")).click();
//		driver.findElement(By.xpath("//div[@class='tab']/button[3]")).click();
		
		//***** TAB 3 INTERACTIONS ******
//		WebElement interactionsTab = driver.findElement(By.xpath("//div[@class='dropdown']/button[contains(text(), 'Intractions')]"));
//		Actions moveToInteractionsTab = new Actions(driver);
//		moveToInteractionsTab.moveToElement(interactionsTab).build().perform();
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//div[@id='user_div']/div/div[2]/div/a[1]")).click();
//		WebElement drag = driver.findElement(By.id("drag1"));
//		WebElement dropTo = driver.findElement(By.xpath("//div[@class='container']/div[1]"));
//		action.clickAndHold(drag).moveToElement(dropTo).release(dropTo).build().perform();
//		Thread.sleep(2000);
//		interactionsTab = driver.findElement(By.xpath("//div[@class='dropdown']/button[contains(text(), 'Intractions')]"));
//		action.moveToElement(interactionsTab).build().perform();
//		driver.findElement(By.xpath("//div[@class='dropdown-content']/a[contains(text(), 'Mouse Hover')]")).click();
//		WebElement mousehover = driver.findElement(By.xpath("//div[@class='dropdown login']/button"));
//		action.moveToElement(mousehover).build().perform();
//		driver.findElement(By.xpath("//div[@class='dropdown login']/div/a[1]")).click();
//		driver.switchTo().alert().accept();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@class='dropdown login']/div/a[2]")).click();
//		driver.switchTo().alert().accept();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@class='dropdown login']/div/a[3]")).click();
//		driver.switchTo().alert().accept();
//		Thread.sleep(2000);
//		interactionsTab = driver.findElement(By.xpath("//div[@class='dropdown']/button[contains(text(), 'Intractions')]"));
//		action.moveToElement(interactionsTab).build().perform();
//		driver.findElement(By.xpath("//div[@class='dropdown-content']/a[contains(text(),  'Double Click')] ")).click();
//		driver.findElement(By.xpath("//div[@id='user_div']/div[2]/div/button[contains(text(), 'single Click')]")).click();
//		WebElement doubleClick = driver.findElement(By.xpath("//div[@id='user_div']/div[2]/div/button[contains(text(), 'Double Click')]"));
//		action.doubleClick(doubleClick).build().perform();
//		interactionsTab = driver.findElement(By.xpath("//div[@class='dropdown']/button[contains(text(), 'Intractions')]"));
//		action.moveToElement(interactionsTab).build().perform();
//		driver.findElement(By.xpath("//div[@class='navbar']/div[2]/div/a[4]")).click();
//		WebElement rightTooltip = driver.findElement(By.xpath("//div[@class='tooltipr']"));
//		action.moveToElement(rightTooltip).build().perform();
//		WebElement leftTooltip = driver.findElement(By.xpath("//div[@class='tooltipl']"));
//		action.moveToElement(leftTooltip).build().perform();
//		WebElement topTooltip = driver.findElement(By.xpath("//div[@class='tooltipt']"));
//		action.moveToElement(topTooltip).build().perform();
//		WebElement bottomTooltip = driver.findElement(By.xpath("//div[@class='tooltipb']"));
//		action.moveToElement(bottomTooltip).build().perform();
		
		//****** WIDGET ******
		WebElement widget = driver.findElement(By.xpath("//button[contains(text(), 'Widget')]"));
		action.moveToElement(widget).build().perform();
//		driver.findElement(By.xpath("//div[@class='navbar']/div[3]/div/a[1]")).click();
		
		//***** TABLE *****
//		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
//		int rowCount = rows.size();
//		List<WebElement> columns = driver.findElements(By.xpath("//table/tbody/tr/th"));
//		int columnCount = columns.size();
//		for(int row = 1 ; row < rowCount+1; row++) {
//			for(int col = 1 ; col < columnCount+1;  col++) {
//				if(row == 1) {
//					String data = "";
//					data = driver.findElement(By.xpath("//table/tbody/tr/th["+col+"]")).getText();
//					System.out.print(data + " ");
//				}else {
//					String data = "";
//					data = driver.findElement(By.xpath("//table/tbody/tr["+row+"]/td["+col+"]/input")).getAttribute("value");
//					System.out.print(data + " ");
//				}
//			}
//			System.out.println();
//		}
		
		
		widget = driver.findElement(By.xpath("//button[contains(text(), 'Widget')]"));
		action.moveToElement(widget).build().perform();
		driver.findElement(By.xpath("//a[contains(text(), 'AutoComplete')]")).click();
		driver.findElement(By.id("myInput")).sendKeys("Co");
		
		
		//****** FILE UPLOAD
//		driver.findElement(By.xpath("//div[@class='navbar']/a[contains(text(), 'File Upload')]")).click();
//		driver.findElement(By.xpath("//button[contains(text(), 'Clear')]")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@id='logo']")).sendKeys("C:/Users/Pallavi/Desktop/Sample.xlsx");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//button[contains(text(), 'Clear')]")).click();
		
		//***** LOGOUT *****
		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@class='navbar']/a[contains(text(), 'Logout')]")).click();
//		driver.close();
	}

}