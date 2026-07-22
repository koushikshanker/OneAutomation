@api
Feature: Restful Booker API
  As an API consumer
  I want to interact with the Restful Booker API
  So that I can manage hotel bookings

  Scenario: Health check returns 201
    Given the booking API is healthy

  Scenario: Create auth token
    When the user creates an auth token

  Scenario: Get all booking IDs
    When the user requests all booking IDs
    Then the booking response status should be 200
    And the booking response should not be empty

  Scenario: Get booking by ID
    When the user requests booking by ID 1
    Then the booking response status should be 200
    And the booking response should not be empty

  Scenario: Filter bookings by name
    When the user filters bookings by firstname "Jim" and lastname "Brown"
    Then the booking response status should be 200

  Scenario: Create a new booking
    When the user creates a booking for "Alice" "Automation" with price 150
    Then the booking response status should be 200
    And the created booking ID should be a positive number
    And the booking response should contain firstname "Alice"

  Scenario: Full update booking with token auth
    Given the booking API is healthy
    When the user creates an auth token
    And the user creates a booking for "UpdateMe" "Before" with price 100
    And the user updates the created booking with token auth for "UpdateMe" "After"
    Then the booking response status should be 200
    And the booking response should contain firstname "UpdateMe"

  Scenario: Partial update booking with PATCH
    Given the booking API is healthy
    When the user creates an auth token
    And the user creates a booking for "PatchMe" "Original" with price 100
    And the user partially updates the created booking firstname to "Patched"
    Then the booking response status should be 200
    And the booking response should contain firstname "Patched"

  Scenario: Delete a booking
    Given the booking API is healthy
    When the user creates an auth token
    And the user creates a booking for "DeleteMe" "TestDelete" with price 50
    And the user deletes the created booking
    Then the booking response status should be 201

  Scenario: Update booking with basic auth
    When the user creates a booking for "BasicAuth" "Test" with price 200
    And the user updates booking 1 with basic auth for "BasicUpdated" "AuthTest"
    Then the booking response status should be 200
