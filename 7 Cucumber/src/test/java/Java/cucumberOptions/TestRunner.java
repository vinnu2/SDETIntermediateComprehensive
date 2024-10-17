package Java.cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		features = "src/test/java/features",
		glue ="stepDefenitions")

public class TestRunner extends AbstractTestNGCucumberTests {

}