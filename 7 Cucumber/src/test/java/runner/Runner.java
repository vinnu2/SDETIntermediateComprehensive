package runner;

import io.cucumber.junit.Cucumber;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "html:target/Destination/reports.html",
		"pretty" }, features = "src/test/java/features", glue = "stepDefenitions", monochrome = true)

public class Runner{

}
