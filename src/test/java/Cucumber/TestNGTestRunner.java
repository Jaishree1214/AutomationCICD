package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src\\test\\java\\Cucumber", glue="AutomationTesting.StepDefinitions", tags="@Regression", monochrome=true, plugin= { "html:target\\Cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
	

}
