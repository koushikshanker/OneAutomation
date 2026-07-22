package com.oneautomation.tests.stepdefinitions.api;

import com.oneautomation.framework.base.be.ApiResponse;
import com.oneautomation.tests.pageobjects.api.HttpBinApiPO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HttpBinApiStepDef {

    private final HttpBinApiPO httpBinApi = new HttpBinApiPO();
    private ApiResponse response;

    @When("the user requests httpbin status {int}")
    public void theUserRequestsStatus(int code) {
        response = httpBinApi.getStatus(code);
    }

    @When("the user requests headers from httpbin")
    public void theUserRequestsHeaders() {
        response = httpBinApi.getHeaders();
    }

    @When("the user requests response headers with {string} set to {string}")
    public void theUserRequestsResponseHeaders(String headerName, String headerValue) {
        response = httpBinApi.getResponseHeaders(headerName, headerValue);
    }

    @When("the user authenticates with basic auth user {string} and password {string}")
    public void theUserAuthenticatesWithBasicAuth(String user, String password) {
        response = httpBinApi.basicAuth(user, password);
    }

    @When("the user calls basic auth without credentials")
    public void theUserCallsBasicAuthWithoutCredentials() {
        response = httpBinApi.basicAuthUnauthorized();
    }

    @When("the user authenticates with bearer token {string}")
    public void theUserAuthenticatesWithBearerToken(String token) {
        response = httpBinApi.bearerAuth(token);
    }

    @When("the user calls bearer auth without token")
    public void theUserCallsBearerAuthWithoutToken() {
        response = httpBinApi.bearerAuthMissing();
    }

    @When("the user requests JSON from httpbin")
    public void theUserRequestsJson() {
        response = httpBinApi.getJson();
    }

    @When("the user posts data {string} to httpbin")
    public void theUserPostsData(String data) {
        response = httpBinApi.postData(data);
    }

    @When("the user requests a UUID from httpbin")
    public void theUserRequestsUuid() {
        response = httpBinApi.getUuid();
    }

    @Then("the httpbin response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus,
                "Httpbin status code mismatch");
    }

    @Then("the httpbin response should contain {string}")
    public void theResponseShouldContain(String expected) {
        Assert.assertTrue(response.getBody().contains(expected),
                "Response should contain '" + expected + "' but was: " + response.getBody());
    }

    @Then("the httpbin response should not be empty")
    public void theResponseShouldNotBeEmpty() {
        Assert.assertNotNull(response.getBody(), "Response should not be null");
        Assert.assertFalse(response.getBody().isEmpty(), "Response should not be empty");
    }

    @Then("the httpbin response should contain header {string}")
    public void theResponseShouldContainHeader(String headerName) {
        Assert.assertTrue(response.getBody().contains(headerName),
                "Response should contain header '" + headerName + "'");
    }

    @Then("the httpbin response should contain uuid field")
    public void theResponseShouldContainUuidField() {
        String uuid = response.jsonPath().getString("uuid");
        Assert.assertNotNull(uuid, "UUID should be present in response");
        Assert.assertFalse(uuid.isEmpty(), "UUID should not be empty");
    }
}
