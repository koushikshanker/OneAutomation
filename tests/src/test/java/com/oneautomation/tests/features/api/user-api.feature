@api
Feature: User API
  As an API consumer
  I want to interact with the user API
  So that I can manage user data

  Scenario: Get all users
    Given the API is available
    When the user requests all users
    Then the response status code should be 200
    And the response should not be empty

  Scenario: Get user by ID
    Given the API is available
    When the user requests user with id "1"
    Then the response status code should be 200
    And the response should not be empty

  Scenario: Create a new user
    Given the API is available
    When the user creates a new user with name "John" and job "Developer"
    Then the response status code should be 201
    And the response body should contain "John"

  Scenario: Delete a user
    Given the API is available
    When the user deletes user with id "1"
    Then the response status code should be 204
