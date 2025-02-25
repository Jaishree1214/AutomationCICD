package AutomationTesting.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationTesting.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	 public WebDriver driver;
	 public LandingPage lp;
	
	public WebDriver intializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\AutomationTesting\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//checking any browser value from command prompt
		String browsername = System.getProperty("browser")!=null?  System.getProperty("browser") : prop.getProperty("browser");
		
		
		
		//chrome
		if(browsername.contains("chrome"))
		{
			ChromeOptions option=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless"))
			{
				option.addArguments("headless");
			}
			
			
			WebDriver driver=new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440,900));
			this.driver= driver;
			
		}
		
		//firefox
		else if (browsername.equalsIgnoreCase("firefox")) 
		{
			
			WebDriver driver=new FirefoxDriver();
			this.driver= driver;
		}
		
		//edge
		else if (browsername.equalsIgnoreCase("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			WebDriver driver=new EdgeDriver();
			this.driver= driver;
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public List<HashMap<String, String>> getJsonDatatoMap(String filepath) throws IOException {
			
			
			//read json to string
			String jsonContent = 	FileUtils.readFileToString(new File(filepath), 
					StandardCharsets.UTF_8);
		
			//string to hashmap jackson databind
			ObjectMapper mapper= new ObjectMapper();
			
			List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference <List<HashMap<String,String>>>(){
				
			});
			return data;
		
		
		}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		//returning screenshot saved path
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}
		
		

	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver= intializeDriver();
		lp= new LandingPage(driver);
		lp.gotopage();
		return lp;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void EndApplication() 
	{
		driver.close();
	}
	
	
	
}
