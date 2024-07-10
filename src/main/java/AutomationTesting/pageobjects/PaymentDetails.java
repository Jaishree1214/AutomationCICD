package AutomationTesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import AutomationTesting.AbstractComponent.AbstractComponent;

public class PaymentDetails extends AbstractComponent {
	
	WebDriver driver;
	
	public PaymentDetails(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	
	public void personalInfo() 
	{
		By crediteditbox= By.xpath("//div/div[@class='field']//input[@class='input txt text-validated']");
		waittoAppear(crediteditbox);
		
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
	}
	
	public void shippingInfo(String country) 
	{
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
	}
	

}
