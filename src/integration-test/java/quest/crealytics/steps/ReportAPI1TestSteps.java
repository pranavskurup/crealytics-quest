package quest.crealytics.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import quest.crealytics.SpringIntegrationTestSupport;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Slf4j
public class ReportAPI1TestSteps extends SpringIntegrationTestSupport {

    @Given("^Rest endpoint '(.*)' request value '(.*)'$")
    public void rest_endpoint_with_request_value(final String endpoint, final String paramValue) {
        setSpec(getWebClient().get().uri(endpoint, paramValue));
    }

    @Given("^Rest endpoint '(.*)' request value for month '(.*)' and for site '(.*)'$")
    public void rest_endpoint_with_request_param_and_value(final String endpoint, final String monthValue, final String siteValue) {
        setSpec(getWebClient().get().uri(endpoint, monthValue, siteValue));
    }


    @Given("^Rest endpoint '(.*)' without params$")
    public void rest_endpoint_without_request_params(final String endpoint) {
        setSpec(getWebClient().get().uri(endpoint));
    }

    @When("^Call rest endpoint$")
    public void call_rest_endpoint() {
        setRes(getSpec().exchange());
    }

    @Then("^Rejected response with status code '(\\d+)' and message '(.*)'$")
    public void rejected_response_with_status_code(final int statusCode, final String message) {
        getRes().expectStatus().isEqualTo(HttpStatus.valueOf(statusCode));
        WebTestClient.BodyContentSpec bodyContentSpec = getRes().expectBody();
        bodyContentSpec.jsonPath("$.statusCode").isNotEmpty();
        bodyContentSpec.jsonPath("$.statusCode").isEqualTo(statusCode);
        bodyContentSpec.jsonPath("$.msg").isNotEmpty();
        bodyContentSpec.jsonPath("$.msg").isEqualTo(message);
    }

    @Then("^Should show data '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)'$")
    public void should_show_data(final String month, final String site, final String ctr, final String cr, final String fillRate, final String ecpm) {
        getRes().expectStatus().isEqualTo(HttpStatus.OK);
        WebTestClient.BodyContentSpec bodyContentSpec = getRes().expectBody();
        bodyContentSpec.jsonPath("$.month").isNotEmpty();
        bodyContentSpec.jsonPath("$.month").isEqualTo(month);
        bodyContentSpec.jsonPath("$.site").isNotEmpty();
        bodyContentSpec.jsonPath("$.site").isEqualTo(site);
        bodyContentSpec.jsonPath("$.CTR").isNotEmpty();
        bodyContentSpec.jsonPath("$.CTR").isEqualTo(ctr);
        bodyContentSpec.jsonPath("$.CR").isNotEmpty();
        bodyContentSpec.jsonPath("$.CR").isEqualTo(cr);
        bodyContentSpec.jsonPath("$.fill_rate").isNotEmpty();
        bodyContentSpec.jsonPath("$.fill_rate").isEqualTo(fillRate);
        bodyContentSpec.jsonPath("$.eCPM").isNotEmpty();
        bodyContentSpec.jsonPath("$.eCPM").isEqualTo(ecpm);
    }
}
