package AutomationTesting.StepDefinitions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import AutomationTesting.Testcomponents.BaseTest;
import AutomationTesting.pageobjects.LandingPage;
import AutomationTesting.pageobjects.MyCart;
import AutomationTesting.pageobjects.PaymentDetails;
import AutomationTesting.pageobjects.ProductCatalogue;
import AutomationTesting.pageobjects.Thankyou;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition extends BaseTest {
	
	public LandingPage lp;
	public ProductCatalogue pc;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		lp= launchApplication();
	}
	
	 @Given("^Logged in with username (.+) and password (.+)$")
	 public void Logged_in_with_username_and_password(String email, String password)
	 {
			pc=lp.login(email,password);
	 }
	 
	 @When("^I add product (.+) to cart$")
	 public void I_add_product_to_cart(String productname)
	 {
		 pc.selectProduct(productname);
	 }
	 
	 @And("^Checkout (.+) and submit order$")
	 public void  Checkout_product_and_submit_order(String productname) throws InterruptedException
	 {
		 MyCart mc=pc.gotoCart();
		 int k= mc.CheckCartItems(productname);
			//System.out.println(productadd.length);
			Assert.assertTrue(k==1);
			PaymentDetails pd=mc.Checkout();
			pd.personalInfo();
			Thread.sleep(3000);
			String country="India";
			pd.shippingInfo(country);
			driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
	 }
	 
	 @Then("{string} message is displayed on confirmation page")
	 public void message_is_displayed_on_confirmation_page(String string) throws InterruptedException
	 {
		 Thankyou ty=new Thankyou(driver);
			String text= ty.verifyOrdermsg();
			Assert.assertTrue(text.equalsIgnoreCase(string));
			driver.close();
	 }
	 
	 @Then ("{string} error message is displayed")
	 public void error_message_is_displayed(String string)
	 {
		 Assert.assertTrue(lp.getErrormsg().equalsIgnoreCase(string));
		 driver.close();
	 }

}
