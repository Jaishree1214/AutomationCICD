package AutomationTesting;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationTesting.Testcomponents.BaseTest;
import AutomationTesting.pageobjects.LandingPage;
import AutomationTesting.pageobjects.MyCart;
import AutomationTesting.pageobjects.OrderHistory;
import AutomationTesting.pageobjects.PaymentDetails;
import AutomationTesting.pageobjects.ProductCatalogue;
import AutomationTesting.pageobjects.Thankyou;

public class SubmitOrderTest extends BaseTest{

	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		//Driver setup
		
		
		// values declaration
		//String useremail="jaishreevari1497@gmail.com";
		//String userpassword="Jaishree@123";
		String[] productadd= {"ZARA COAT 3","IPHONE 13 PRO"};
	
		
		
		//starting driver
		//WebDriver driver= intializeDriver();
		
		
		
		//LandingPage lp= new LandingPage(driver);
		
		//login page
		ProductCatalogue pc=lp.login(input.get("useremail"), input.get("Password"));
		
		
		//product catalogue
		
		
		//adding items to the cart
		pc.selectProduct(productadd);
		MyCart mc=pc.gotoCart();
		
		//checking the cart items
		
		int k= mc.CheckCartItems(productadd);
		//System.out.println(productadd.length);
		Assert.assertTrue(k==productadd.length);
		
		//scrollong the page
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 8000)");
		Thread.sleep(2000);
		
		
		//checkout
		PaymentDetails pd=mc.Checkout();
		
		//Thread.sleep(3000);
		
		//filling details in Payment page
		pd.personalInfo();
		Thread.sleep(3000);
		String country="India";
		pd.shippingInfo(country);
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		//Thankyou page text veriying
		Thankyou ty=new Thankyou(driver);
		boolean text= ty.verifyOrder();
		Assert.assertTrue(text);
	}
	
	

	@Test (dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() throws InterruptedException 
	{
		//Driver setup
		
		
		// values declaration
		String useremail="jaishreevari1497@gmail.com";
		String userpassword="Jaishree@123";
		String[] productadd= {"ZARA COAT 3","IPHONE 13 PRO"};
		
		//login page
		lp.login(useremail, userpassword);
		OrderHistory oh=lp.gotoOrders();
		//Thread.sleep(2000);
		//System.out.println(oh.VerifyOrderDisplay(productadd));
		Assert.assertTrue(oh.VerifyOrderDisplay(productadd));;
	}
	
	
	 @DataProvider
	  public Object[][] getData() throws IOException
	  {
			String filepath=System.getProperty("user.dir")+"\\src\\test\\java\\AutomationTesting\\Data\\PurchaseOrder.json";
		 List<HashMap<String,String>> data =getJsonDatatoMap(filepath);
		 return new Object[][] {{data.get(0)}, {data.get(1) } };
		 
		 /*
			 * HashMap<String,String> map = new HashMap<String,String>();
			 * map.put("useremail", "jaishreevari1497@gmail.com"); map.put("Password",
			 * "Jaishree@123");
			 * 
			 * 
			 * HashMap<String,String> map1 = new HashMap<String,String>();
			 * map1.put("useremail", "jaishree@gmail.com"); map1.put("Password",
			 * "Jaishree@123");
			 * 
			 * return new Object[][] {{map}, {map1 } };
			 */
  
	  }		
		
		
		
		  
		
		

}