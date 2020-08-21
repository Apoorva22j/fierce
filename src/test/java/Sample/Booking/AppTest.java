package Sample.Booking;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
   
    @Test
    public void shouldAnswerWithTrue()
    {
    
      //WebDriver driver = new ChromeDriver();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--incognito");
      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      capabilities.setCapability(ChromeOptions.CAPABILITY, options);
      WebDriver driver = new ChromeDriver(capabilities);
      
      //Go to https://www.cleartrip.com/
      driver.get("https://www.cleartrip.com");
        assertTrue(true);
        //Search 'Round Trip' from Mum to Del. Select date.
        driver.findElement(By.id("RoundTrip")).click();
		driver.findElement(By.id("FromTag")).sendKeys("Mumbai, IN - Chatrapati Shivaji Airport (BOM)");
		driver.findElement(By.id("ToTag")).sendKeys("New Delhi, IN - Indira Gandhi Airport (DEL)");
		driver.findElement(By.id("DepartDate")).click();
		driver.findElement(By.xpath("//*[@id=\'ui-datepicker-div\']/div[1]/table/tbody/tr[5]/td[1]/a")).click();
		driver.findElement(By.id("ReturnDate")).click();
		driver.findElement(By.xpath("//*[@id=\'ui-datepicker-div\']/div[1]/table/tbody/tr[5]/td[2]/a")).click();		
		
		//Select Adults travelling = 2
		Select S1 = new Select(driver.findElement(By.id("Adults")));
		S1.selectByValue("2");
		
		driver.findElement(By.id("SearchBtn")).click();
		WebDriverWait wait = new WebDriverWait(driver, 35);
		WebElement Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'root\']/div/main/div/div/div[2]/div[2]/div[10]/div/div[2]/div/div[1]/a/span")));
		
        //To maximize the window	
        driver.manage().window().maximize();
        
        //Scroll Down
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollTo(0, 4000)");
       
    	//Filter Airlines
    	Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/main/div/div/div[1]/div/aside/div[4]/div[2]/div[2]/div/label[1]/div[1]/span")));
		Category_Body.click();
		Actions action = new Actions(driver);
		WebElement a = driver.findElement(By.xpath("//*[@id=\'root\']/div/main/div/div/div[1]/div/aside/div[4]/div[6]/div[2]/div/label[6]/div[2]"));
		action.moveToElement(a).moveToElement(driver.findElement(By.xpath("//*[@id=\'root\']/div/main/div/div/div[1]/div/aside/div[4]/div[6]/div[2]/div/label[6]/div[2]/span[1]/span"))).click().build().perform();
		js.executeScript("window.scrollTo(0, 4000)");
		driver.findElement(By.xpath("//*[@id=\'root\']/div/main/div/div/div[1]/div/aside/div[4]/div[6]/div[2]/div/label[4]/div[1]")).click();
	
		
		//Select flights
		js.executeScript("window.scrollTo(0, -3000)");
		driver.findElement(By.xpath("//*[@id=\'root\']/div/main/div/div/div[2]/div[2]/div[12]/div[1]/div[1]/div/div[2]")).click();
		
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'root\']/div/main/div/div/div[2]/div[2]/div[12]/div[2]/div[1]/div/div[4]")));
		Category_Body.click();
		
		//Click Book
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'root\']/div/main/div/div/div[2]/div[2]/div[7]/div/div[2]/button")));
		Category_Body.click();
	
		//Accept T&C
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		 
		// Switch newly open Tab
		driver.switchTo().window(tabs.get(1));
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#step1Open")));
		js.executeScript("window.scrollTo(0, 4000)");
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("insurance_confirm")));
		Category_Body.click();
		 
    	
    	//Click 'Continue Booking'
    	Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itineraryBtn")));
		Category_Body.click();
		
		//Enter an email address
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		Category_Body.sendKeys("verve@sample.com");
		
		//Click 'Continue'
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LoginContinueBtn_1")));
		Category_Body.click();
		
		//Enter traveller details
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AdultTitle1")));
		S1 = new Select(driver.findElement(By.id("AdultTitle1")));
		S1.selectByIndex(1);
		driver.findElement(By.id("AdultFname1")).sendKeys("Harry");
		driver.findElement(By.id("AdultLname1")).sendKeys("Potter");
		
		S1 = new Select(driver.findElement(By.id("AdultTitle2")));
		S1.selectByIndex(3);
		driver.findElement(By.id("AdultFname2")).sendKeys("Hermione");
		driver.findElement(By.id("AdultLname2")).sendKeys("Granger");
		driver.findElement(By.id("mobileNumber")).sendKeys("9876543210");
		
		//Click 'Continue'
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("travellerBtn")));
		Category_Body.click();
		
		//On the Payments page, use net banking and select 'Kotak Bank' and Click 'Payment'
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("NBTab")));
		Category_Body.click();
		driver.findElement(By.id("kotak_bank")).click();
		driver.findElement(By.id("paymentSubmit")).click();
		
		//Verify Kotak page
		Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'ui-id-1\']/span[1]")));
		if(driver.getTitle().contains("Kotak"))
			System.out.println("Kotak Net Banking page has opened up");
		else
			System.out.println(driver.getTitle());
			
    }
}
