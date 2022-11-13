package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@Smoke and @Registration",
        plugin = {"pretty", "html:target/cucumber-html-report.html"},
        monochrome = true,
        dryRun = false
)

public class SmokeRunner {
}
