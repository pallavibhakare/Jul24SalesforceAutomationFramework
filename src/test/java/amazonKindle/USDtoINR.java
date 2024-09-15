package amazonKindle;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class USDtoINR {
	public static WebDriver driver;
	public static void main(String[] args)  {
		
		driver = new ChromeDriver();			
		driver.get("https://www.xe.com/");
		
		driver.manage().window().maximize();
		WebElement formField = driver.findElement(By.xpath("//div[@id='midmarketFromCurrency']"));
		formField.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='midmarketFromCurrency-listbox']")));
		WebElement fromList = driver.findElement(By.xpath("//ul[@id='midmarketFromCurrency-listbox']"));
		List<String>countries = new ArrayList<>();
		List<WebElement> fromElements = fromList.findElements(By.xpath("//ul[@id='midmarketFromCurrency-listbox']/li"));
		for(WebElement from : fromElements) {
			countries.add(from.getText());
			if(from.getText().contains("USD")) {
				from.click();
				System.out.println("Option list with USD is clicked");
				break; 
			}
		}
		
		WebElement toField = driver.findElement(By.xpath("//div[@id='midmarketToCurrency']"));
		toField.click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='midmarketToCurrency-listbox']")));
		WebElement toList = driver.findElement(By.xpath("//ul[@id='midmarketToCurrency-listbox']"));
		List<String> countries2 = new ArrayList<String>();
		List<WebElement>toElements = toList.findElements(By.xpath("//ul[@id='midmarketToCurrency-listbox']/li"));
		for(WebElement to : toElements) {
			countries2.add(to.getText());
			if(to.getText().contains("INR")) {
				to.click();
				System.out.println("Option list with INR is clicked");
				break; 
			}
		}
		
		WebElement convertbutton=driver.findElement(By.xpath("//div[@class='sc-4f0f6f94-2 chRjcw']/button"));
		convertbutton.click();
		
        CaptureScreenShots(driver);
       
//        driver.close();
        
}


	private static void CaptureScreenShots(WebDriver driver) {
		TakesScreenshot page = (TakesScreenshot) driver;
		File src = page.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"/src/main/resources/USDtoINR.png");
		src.renameTo(destination);
		
	}



}
