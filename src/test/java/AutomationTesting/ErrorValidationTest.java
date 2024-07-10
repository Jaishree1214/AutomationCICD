package AutomationTesting;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTesting.Testcomponents.BaseTest;
import AutomationTesting.Testcomponents.Retry;
import AutomationTesting.pageobjects.LandingPage;
import AutomationTesting.pageobjects.MyCart;
import AutomationTesting.pageobjects.PaymentDetails;
import AutomationTesting.pageobjects.ProductCatalogue;
import AutomationTesting.pageobjects.Thankyou;

public class ErrorValidationTest extends BaseTest{

	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginPageErrorValidation() throws IOException, InterruptedException {
		// values declaration
		String useremail="jaishreevari1497@gmail.com";
		String userpassword="Jaishreedjh@123";

		lp.gotopage();
		lp.login(useremail, userpassword);
		Assert.assertEquals("Incorrect email or password.",lp.getErrormsg());		
		
	}
	
	@Test
	public void ProductAddedValidation() throws IOException, InterruptedException {
		//Driver setup
		
		
		// values declaration
		String useremail="jaishreevari1497@gmail.com";
		String userpassword="Jaishree@123";
		String[] productadd= {"ZARA COAT 3","IPHONE 13 PRO"};
		//starting driver
		//WebDriver driver= intializeDriver();
		//LandingPage lp= new LandingPage(driver);
		//login page
		//product catalogue
		ProductCatalogue pc=lp.login(useremail, userpassword);
		//adding items to the cart
		pc.selectProduct(productadd);
		MyCart mc=pc.gotoCart();
		//checking the cart items
		int k= mc.CheckCartItems(productadd);
		//System.out.println(productadd.length);
		Assert.assertTrue(k==productadd.length);
	}

}
