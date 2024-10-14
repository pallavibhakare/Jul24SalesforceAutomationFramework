package stepdefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		glue = "stepdefinitions", 
		features = "src/test/java/features",
		monochrome = true,
		plugin = {"pretty", "html:target/reports.html"}
		)
public class TestRunner {

}
