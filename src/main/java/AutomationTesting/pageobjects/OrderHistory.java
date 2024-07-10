package AutomationTesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import AutomationTesting.AbstractComponent.AbstractComponent;

public class OrderHistory extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderHistory(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
	}
	
	
	public boolean VerifyOrderDisplay(String[] productadd) 
	{
		
		
		String actual; int k=0; boolean bool=false;
		
		
		List<WebElement> ordereditems= driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
				
				for(int i=0;i<ordereditems.size();i++) 
				{	
						actual=ordereditems.get(i).getText();
						for(int j=0;j<productadd.length;j++)
						{
							if(productadd[j].equalsIgnoreCase(actual))
									{
								k++;
									}
						}
							
					if(k==productadd.length)
					{
						bool=true;
						break;
					}
				}
				return bool;
				
	}
}
	
	
