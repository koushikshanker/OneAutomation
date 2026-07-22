package com.oneautomation.tests.stepdefinitions.api;

import com.oneautomation.framework.base.be.ApiResponse;
import com.oneautomation.tests.pageobjects.api.BookingApiPO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class BookingApiStepDef {

    private final BookingApiPO bookingApi = new BookingApiPO();
    private ApiResponse response;
    private String authToken;
    private int createdBookingId;

    @Given("the booking API is healthy")
    public void theBookingApiIsHealthy() {
        response = bookingApi.healthCheck();
        Assert.assertEquals(response.getStatusCode(), 201,
                "Health check should return 201");
    }

    @When("the user creates an auth token")
    public void theUserCreatesAnAuthToken() {
        authToken = bookingApi.createAuthToken();
        Assert.assertNotNull(authToken, "Auth token should not be null");
    }

    @When("the user requests all booking IDs")
    public void theUserRequestsAllBookingIds() {
        response = bookingApi.getAllBookingIds();
    }

    @When("the user requests booking by ID {int}")
    public void theUserRequestsBookingById(int id) {
        response = bookingApi.getBookingById(id);
    }

    @When("the user filters bookings by firstname {string} and lastname {string}")
    public void theUserFiltersBookingsByName(String firstname, String lastname) {
        response = bookingApi.filterBookingsByName(firstname, lastname);
    }

    @When("the user creates a booking for {string} {string} with price {int}")
    public void theUserCreatesABooking(String firstname, String lastname, int price) {
        response = bookingApi.createBooking(firstname, lastname, price,
                true, "2025-01-01", "2025-01-05");
        if (response.getStatusCode() == 200) {
            createdBookingId = response.jsonPath().getInt("bookingid");
        }
    }

    @When("the user updates booking {int} with token auth for {string} {string}")
    public void theUserUpdatesBookingWithTokenAuth(int id, String firstname, String lastname) {
        Assert.assertNotNull(authToken, "Auth token must be created first");
        response = bookingApi.updateBooking(id, authToken, firstname, lastname,
                200, true, "2025-02-01", "2025-02-10");
    }

    @When("the user updates the created booking with token auth for {string} {string}")
    public void theUserUpdatesCreatedBookingWithTokenAuth(String firstname, String lastname) {
        Assert.assertTrue(createdBookingId > 0, "A booking must be created first");
        Assert.assertNotNull(authToken, "Auth token must be created first");
        response = bookingApi.updateBooking(createdBookingId, authToken, firstname, lastname,
                250, false, "2025-03-01", "2025-03-07");
    }

    @When("the user partially updates the created booking firstname to {string}")
    public void theUserPartiallyUpdatesBooking(String firstname) {
        Assert.assertTrue(createdBookingId > 0, "A booking must be created first");
        Assert.assertNotNull(authToken, "Auth token must be created first");
        response = bookingApi.partialUpdateBooking(createdBookingId, authToken, firstname);
    }

    @When("the user deletes the created booking")
    public void theUserDeletesTheCreatedBooking() {
        Assert.assertTrue(createdBookingId > 0, "A booking must be created first");
        Assert.assertNotNull(authToken, "Auth token must be created first");
        response = bookingApi.deleteBooking(createdBookingId, authToken);
    }

    @When("the user updates booking {int} with basic auth for {string} {string}")
    public void theUserUpdatesBookingWithBasicAuth(int id, String firstname, String lastname) {
        response = bookingApi.updateBookingWithBasicAuth(id, firstname, lastname,
                300, true, "2025-04-01", "2025-04-05");
    }

    @Then("the booking response status should be {int}")
    public void theBookingResponseStatusShouldBe(int expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus,
                "Booking API status code mismatch");
    }

    @Then("the booking response body should contain {string}")
    public void theBookingResponseBodyShouldContain(String expected) {
        Assert.assertTrue(response.getBody().contains(expected),
                "Response body should contain '" + expected + "' but was: " + response.getBody());
    }

    @Then("the booking response should not be empty")
    public void theBookingResponseShouldNotBeEmpty() {
        Assert.assertNotNull(response.getBody(), "Response body should not be null");
        Assert.assertFalse(response.getBody().isEmpty(), "Response body should not be empty");
    }

    @Then("the created booking ID should be a positive number")
    public void theCreatedBookingIdShouldBePositive() {
        Assert.assertTrue(createdBookingId > 0,
                "Created booking ID should be positive, was: " + createdBookingId);
    }

    @Then("the booking response should contain firstname {string}")
    public void theBookingResponseShouldContainFirstname(String firstname) {
        String actualFirstname = response.jsonPath().getString("firstname");
        if (actualFirstname == null) {
            actualFirstname = response.jsonPath().getString("booking.firstname");
        }
        Assert.assertEquals(actualFirstname, firstname);
    }
}
