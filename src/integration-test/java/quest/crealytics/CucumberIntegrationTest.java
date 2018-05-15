package quest.crealytics;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/integration-test/resources/features", tags = {"@Auto"}, plugin = {"pretty", "html:target/cucumber-html-reports", "json:target/cucumber-html-reports/cucumber.json"})
public class CucumberIntegrationTest {
}
