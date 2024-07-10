package AutomationTesting;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationTesting.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		//Driver setup
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// values declaration
		String useremail="jaishreevari1497@gmail.com";
		String userpassword="Jaishree@123";
		String[] productadd= {"ZARA COAT 3","IPHONE 13 PRO"};
		
		
		//starting driver
		
		
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys(useremail);;
		driver.findElement(By.id("userPassword")).sendKeys(userpassword);;
		driver.findElement(By.id("login")).click();
	
		
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		//login
		//driver.findElement(By.id("userEmail")).sendKeys(useremail);
		//driver.findElement(By.id("userPassword")).sendKeys(userpassword);
		//driver.findElement(By.id("login")).click();
		
		//identifying products
		List<WebElement> products= driver.findElements(By.xpath("//div[@class='card-body']/h5"));
		
		//adding items using java stream
		
	
		
		//adding items using array
		for(int i=0;i< products.size();i++) 
			
		{
			//WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
			//
			
			for(int j=0;j< productadd.length;j++) 
			{
				if(productadd[j].contains(products.get(i).getText()))
				{
				
					driver.findElements(By.cssSelector(".card-body button:last-of-type")).get(i).click();
					
					//explicitwait for product added alert to disappear
					w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
					w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
					
				}
			}
			
		}
		driver.findElement(By.xpath("//li//i[@class='fa fa-shopping-cart']")).click();
		
		
		//checking expected products added
		List<WebElement> cartitems= driver.findElements(By.xpath("//div[@class='cart']/ul//h3"));
		
		//vertifying through for loop
		String actual; int k=0;
		
		for(int i=0;i<cartitems.size();i++) {	
				actual=cartitems.get(i).getText();
			while(k<productadd.length) {
				if(productadd[k].matches(actual))
					{
					k++;
					break;
					}
			}
		}
		Assert.assertTrue(k==2);
		
		
		
	
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,10000)");
		
		Thread.sleep(3000);
		
		//w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
		
		WebElement checkoutbutton=driver.findElement(By.cssSelector(".totalRow button"));
		
		checkoutbutton.click();

		
		
		//Thread.sleep(3000);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div[@class='field']//input[@class='input txt text-validated']")));
		
		driver.findElement(By.xpath("//div/div[@class='field']//input[@class='input txt text-validated']")).sendKeys("7357365838743");
		WebElement drop= driver.findElement(By.xpath("//select[@class='input ddl'][1]"));
		Select s=new Select(drop);
		s.selectByIndex(10);
		WebElement drop2= driver.findElement(By.xpath("//select[@class='input ddl'][2]"));
		Select s1=new Select(drop2);
		s1.selectByIndex(15);
		driver.findElement(By.xpath("//div[@class='field small']/input[@class='input txt']")).sendKeys("634387");
		driver.findElement(By.xpath("//div[@class='field']/input[@class='input txt']")).sendKeys("jaishree");
		driver.findElement(By.name("coupon")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector(".btn.btn-primary.mt-1")).click();
		Thread.sleep(3000);
		//w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted button")));
		
		String country="India";
		driver.findElement(By.cssSelector(".input.txt.text-validated[placeholder='Select Country']")).sendKeys(country);

		List<WebElement> countrydrop= driver.findElements(By.cssSelector(".ta-results.list-group.ng-star-inserted button"));
		for(int i=0;i<countrydrop.size();i++)
		{
			String sele= countrydrop.get(i).getText();
			if(countrydrop.get(i).getText().equalsIgnoreCase(country))
			{
				
				countrydrop.get(i).click();
				break;
			}
		}
		
		
		
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
		
	
	
		
		
		
		
		

	}

}
