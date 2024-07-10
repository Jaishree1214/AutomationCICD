package AutomationTesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import AutomationTesting.AbstractComponent.AbstractComponent;

public class MyCart extends AbstractComponent{
	
	WebDriver driver;
	
	public MyCart(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
	}
	
	
	


	
	public int CheckCartItems(String[] productadd) 
	{
		
		
		String actual; int k=0;
		
		List<WebElement> cartitems= driver.findElements(By.xpath("//div[@class='cart']/ul//h3"));
				
				for(int i=0;i<cartitems.size();i++) 
				{	
						actual=cartitems.get(i).getText();
						
					while(k<productadd.length) {
						if(productadd[k].matches(actual))
							{
							k++;
							break;
							}
					}
				}
				return k;
	}
	
	public int CheckCartItems(String productadd) 
	{
		
		
		String actual; int k=0;
		
		List<WebElement> cartitems= driver.findElements(By.xpath("//div[@class='cart']/ul//h3"));
				
				for(int i=0;i<cartitems.size();i++) 
				{	
						actual=cartitems.get(i).getText();
						
					while(k<1) {
						if(productadd.matches(actual))
							{
							k++;
							break;
							}
					}
				}
				return k;
	}
	
	public PaymentDetails Checkout() 
	{
		WebElement checkoutbutton=driver.findElement(By.cssSelector(".totalRow button"));
		
		checkoutbutton.click();
		PaymentDetails pd=new PaymentDetails(driver);
		return pd;
	}

}
