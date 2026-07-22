package com.oneautomation.tests.stepdefinitions.api;

import com.oneautomation.framework.base.be.ApiResponse;
import com.oneautomation.tests.pageobjects.api.UserApiPO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class UserApiStepDef {

    private final UserApiPO userApi = new UserApiPO();
    private ApiResponse response;

    @Given("the API is available")
    public void theApiIsAvailable() {
        // API availability is handled by configuration
    }

    @When("the user requests all users")
    public void theUserRequestsAllUsers() {
        response = userApi.getUsers();
    }

    @When("the user requests user with id {string}")
    public void theUserRequestsUserById(String userId) {
        response = userApi.getUserById(userId);
    }

    @When("the user creates a new user with name {string} and job {string}")
    public void theUserCreatesNewUser(String name, String job) {
        response = userApi.createUser(name, job);
    }

    @When("the user deletes user with id {string}")
    public void theUserDeletesUser(String userId) {
        response = userApi.deleteUser(userId);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    @Then("the response body should contain {string}")
    public void theResponseBodyShouldContain(String expectedContent) {
        Assert.assertTrue(response.getBody().contains(expectedContent),
                "Response body should contain: " + expectedContent);
    }

    @Then("the response should not be empty")
    public void theResponseShouldNotBeEmpty() {
        Assert.assertNotNull(response.getBody());
        Assert.assertFalse(response.getBody().isEmpty(), "Response body should not be empty");
    }
}
