package AutomationTesting.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationTesting.pageobjects.MyCart;
import AutomationTesting.pageobjects.OrderHistory;

public class AbstractComponent {
	
	WebDriver driver;
	
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}


	public MyCart gotoCart() 
	{
		WebElement cartbutton= driver.findElement(By.xpath("//li//i[@class='fa fa-shopping-cart']"));
		cartbutton.click();
		MyCart mc= new MyCart(driver);
		return mc;

	} 
	
	public OrderHistory gotoOrders() 
	{
		WebElement orderbutton= driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/myorders']"));
		orderbutton.click();
		OrderHistory oh=new OrderHistory(driver);
		return oh;

	} 
	
	public void waittoAppear(By findelement) 
	{
		WebDriverWait w=new WebDriverWait(driver,Duration.ofMillis(10000));
		w.until(ExpectedConditions.visibilityOfElementLocated(findelement));
	}
	
	public void waittoAppear(WebElement findelement) 
	{
		WebDriverWait w=new WebDriverWait(driver,Duration.ofMillis(10000));
		w.until(ExpectedConditions.visibilityOf(findelement));
	}
	
	public void waittoDisappear(By findelement) 
	{
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.invisibilityOfElementLocated(findelement));
	}
	
	
	

}
