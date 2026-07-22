@api
Feature: Httpbin API Testing
  As an API tester
  I want to test various HTTP scenarios using httpbin
  So that I can validate headers, auth, and status codes

  Scenario: Get 200 status code
    When the user requests httpbin status 200
    Then the httpbin response status should be 200

  Scenario: Get 404 status code
    When the user requests httpbin status 404
    Then the httpbin response status should be 404

  Scenario: Get 500 status code
    When the user requests httpbin status 500
    Then the httpbin response status should be 500

  Scenario: Validate custom headers are echoed
    When the user requests headers from httpbin
    Then the httpbin response status should be 200
    And the httpbin response should contain "X-Custom-Header"
    And the httpbin response should contain "TestValue123"

  Scenario: Validate response headers
    When the user requests response headers with "X-Test" set to "AutomationValue"
    Then the httpbin response status should be 200
    And the httpbin response should contain "AutomationValue"

  Scenario: Basic auth with valid credentials
    When the user authenticates with basic auth user "admin" and password "secret"
    Then the httpbin response status should be 200
    And the httpbin response should contain "authenticated"

  Scenario: Basic auth without credentials returns 401
    When the user calls basic auth without credentials
    Then the httpbin response status should be 401

  Scenario: Bearer token auth with valid token
    When the user authenticates with bearer token "test-token-123"
    Then the httpbin response status should be 200
    And the httpbin response should contain "authenticated"

  Scenario: Bearer auth without token returns 401
    When the user calls bearer auth without token
    Then the httpbin response status should be 401

  Scenario: Get JSON response structure
    When the user requests JSON from httpbin
    Then the httpbin response status should be 200
    And the httpbin response should not be empty
    And the httpbin response should contain "slideshow"

  Scenario: Post data and verify echo
    When the user posts data "{\"name\":\"test\",\"value\":42}" to httpbin
    Then the httpbin response status should be 200
    And the httpbin response should contain "test"

  Scenario: Get UUID and validate format
    When the user requests a UUID from httpbin
    Then the httpbin response status should be 200
    And the httpbin response should contain uuid field
