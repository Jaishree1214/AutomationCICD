package AutomationTesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import AutomationTesting.AbstractComponent.AbstractComponent;

public class Thankyou extends AbstractComponent{
	
	WebDriver driver;
	
	public Thankyou (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public boolean verifyOrder()
	{
		String text=driver.findElement(By.cssSelector(".hero-primary")).getText();
		boolean bool = false;
	
		if (text.equalsIgnoreCase("THANKYOU FOR THE ORDER."))
		{
		bool=true;
		}
		return bool;
	}
	public String verifyOrdermsg()
	{
		String text;
		return text=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
	}
	
	

}
