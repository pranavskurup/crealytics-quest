package quest.crealytics.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import quest.crealytics.SpringIntegrationTestSupport;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Slf4j
public class ReportAPI1TestSteps extends SpringIntegrationTestSupport {

    @Given("^Rest endpoint '(.*)' request value '(.*)'$")
    public void rest_endpoint_with_request_value(final String endpoint, final String paramValue) {

    }

    @Given("^Rest endpoint '(.*)' request value for month '(.*)' and for site '(.*)'$")
    public void rest_endpoint_with_request_param_and_value(final String endpoint, final String monthValue, final String siteValue) {

    }


    @Given("^Rest endpoint '(.*)' without params$")
    public void rest_endpoint_without_request_params(final String endpoint) {

    }

    @When("^Call rest endpoint$")
    public void call_rest_endpoint() {

    }

    @Then("^Rejected response with status code '(\\d+)' and message '(.*)'$")
    public void rejected_response_with_status_code(final int statusCode, final String message){

    }

    @Then("^Should show data '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)'$")
    public void should_show_data(final String month, final String site, final String ctr, final String cr, final String fillRate, final String ecpm) {

    }
}
