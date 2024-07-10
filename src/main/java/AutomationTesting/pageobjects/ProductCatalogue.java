package AutomationTesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AutomationTesting.AbstractComponent.AbstractComponent;

public  class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement uemail= driver.findElement(By.id("userEmail"));
	//WebElement upassword= driver.findElement(By.id("userPassword"));
	

	@FindBy(xpath="//div[@class='card-body']/h5") List<WebElement> productlist;
	
	public void selectProduct(String[] productadd)
	{
		for(int i=0;i< productlist.size();i++) 
					
				{
					//WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
					//
					
					for(int j=0;j< productadd.length;j++) 
					{
						if(productadd[j].contains(productlist.get(i).getText()))
						{
						
							driver.findElements(By.cssSelector(".card-body button:last-of-type")).get(i).click();
							
							//explicitwait for product added alert to disappear
							By toastmsg=By.cssSelector("#toast-container");
							waittoAppear(toastmsg);
							waittoDisappear(toastmsg);
							
						}
					}
					
				}
					}
	public void selectProduct(String productadd)
	{
		for(int i=0;i< productlist.size();i++) 
					
				{
					//WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
					//
				
						if(productadd.contains(productlist.get(i).getText()))
						{
						
							driver.findElements(By.cssSelector(".card-body button:last-of-type")).get(i).click();
							
							//explicitwait for product added alert to disappear
							By toastmsg=By.cssSelector("#toast-container");
							waittoAppear(toastmsg);
							waittoDisappear(toastmsg);
							
						}
					}
					
				}
	
	 

}
