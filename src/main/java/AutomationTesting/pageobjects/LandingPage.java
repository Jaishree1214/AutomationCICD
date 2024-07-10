package AutomationTesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.AbstractComponent.AbstractComponent;

public  class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement uemail= driver.findElement(By.id("userEmail"));
	//WebElement upassword= driver.findElement(By.id("userPassword"));
	

	@FindBy(id="userEmail") WebElement uemail;
	@FindBy(id="userPassword") WebElement upassword;
	@FindBy(id="login") WebElement loginbut;
	@FindBy(css="[class*='flyInOut']") WebElement errormsg;
	
	public ProductCatalogue login(String email, String password) 
	{
		uemail.sendKeys(email);
		upassword.sendKeys(password);
		loginbut.click();
		ProductCatalogue pc=new ProductCatalogue(driver);
		return pc;
	}
	
	public void gotopage()
	{
		String url="https://rahulshettyacademy.com/client";
		driver.get(url);
	}

	public String getErrormsg()
	{
		waittoAppear(errormsg);
		return errormsg.getText();
	}

}
