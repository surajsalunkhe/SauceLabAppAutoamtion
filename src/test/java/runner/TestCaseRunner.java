package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src\\test\\resources\\features",
        tags = "@SauceLabAppTC",
        glue = {"stepDefinitions", "Hooks"},
        plugin = {"pretty",
                "html:target\\cucumber-reports\\report.html"
        },
        stepNotifications = true
)

public class TestCaseRunner {
}
