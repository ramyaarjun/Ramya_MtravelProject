package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features/",
        glue="Stepdefinition",
       // plugin = {"pretty", "html:target/cucumber-reports"}
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }
)

public class TestRunner {
}


