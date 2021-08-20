package precisely.qa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import precisely.excel.utility.ReadExcelData;

import java.io.IOException;
import java.lang.*;

public class automation_test {
	
	String AfterFormSubmission_Text = "Thank You!";
	String Expected_target = "https://www.infogix.com/solutions/regulatory-compliance/";
	String regcom_target;
	
	WebDriver driver;
	
    @BeforeMethod
	
	public void setUp() {
	
	
	System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");	
	
    driver= new ChromeDriver();					
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
    driver.get("https://www.infogix.com/");
    
    }
    
   @DataProvider
   public Iterator<Object[]> getTestData() throws IOException{
	   
	   ReadExcelData excelread = new ReadExcelData();
	   
	   ArrayList<Object[]> testdata = excelread.getDataFromExcel();
	   return testdata.iterator();
   }
   
   @Test(dataProvider="getTestData")
    public void formSubmission(String FirstName,String LastName, String Company, String Email, String Phone,String Location, String Industry, String Comment) {
    	
    	driver.findElement(By.cssSelector("ul#menu-utility li:nth-of-type(1)")).click();
    	
    	
    	driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(FirstName);
    	
    	driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(LastName);
    	
    	driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(Company);
    	
    	driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(Email);
    	
    	driver.findElement(By.xpath("//input[@id='Phone']")).sendKeys(Phone);
    	
    	WebElement country_dropdown = driver.findElement(By.xpath("//select[@id='HQ_Location_Country__c']"));
    	
    	Select dropdwn_list = new Select(country_dropdown);
    	
    	dropdwn_list.selectByVisibleText(Location);
    	
    	WebElement industry_dropdown = driver.findElement(By.xpath("//select[@id='Industry__c']"));
    	
    	Select indsry_dropdwn_list = new Select(industry_dropdown);
    	
    	indsry_dropdwn_list.selectByVisibleText(Industry);
    	
    	driver.findElement(By.xpath("//textarea[@id='Next_Step_Comments__c']")).sendKeys(Comment);
    
    	WebDriverWait wait = new WebDriverWait(driver, 20);
	    WebElement allow_all = driver.findElement(By.cssSelector("div#adroll_allow_all"));
	    wait.until(ExpectedConditions.elementToBeClickable(allow_all)).click();
    	
    	WebElement checkbox = driver.findElement(By.xpath("//label[@id='LblConsent_to_Processing__c']"));
    	checkbox.click();
    	
    	driver.findElement(By.xpath("//*[text()='Submit']")).click();
    	
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	String after_submit = driver.findElement(By.cssSelector("div.callout-card>h1.withline")).getText();
    	
       
        
        Assert.assertEquals(after_submit, AfterFormSubmission_Text);
    } 
    
    
    @Test
    public void searchGovernValidation() {
    	
    	
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	
    	WebDriverWait wait = new WebDriverWait(driver, 30);
	    WebElement allow_all = driver.findElement(By.xpath("//*[text()='Allow All']"));
	    wait.until(ExpectedConditions.elementToBeClickable(allow_all)).click();
	    
    	
    	driver.findElement(By.cssSelector("a.search-site")).click();
    	
    	
    	
    	
    	driver.findElement(By.xpath("//input[@name='s']")).sendKeys("govern");
    	WebElement search = driver.findElement(By.xpath("//input[@name='s']"));
    	search.sendKeys(Keys.ENTER);
    	
    	
    	driver.findElement(By.xpath("//a[text()='Next Page']")).click();
    	
    	driver.findElement(By.xpath("//*[text()='Building Data Trust with Strategic Data Governance']")).click();
    	
    	driver.findElement(By.xpath("//*[text()='regulatory compliance']")).click();
    	
    	String ParentWindow = driver.getWindowHandle();
    	Set <String> windows = driver.getWindowHandles();
    	Iterator <String> itr = windows.iterator();
    	
    	while(itr.hasNext()) {
    		String childwindow = itr.next();
    		if(!ParentWindow.equals(childwindow)) {
    			driver.switchTo().window(childwindow);
    			 regcom_target = driver.switchTo().window(childwindow).getCurrentUrl();
    			System.out.println(regcom_target);
    			
    		}
    	}
    	driver.switchTo().window(ParentWindow);
    	
    	Assert.assertEquals(regcom_target, Expected_target);
    	
    	
    }
    
   @AfterMethod
    public void close() {
 	   
 	   driver.quit();
    }
    

}
