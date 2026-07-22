@ui @smoke
Feature: OrangeHRM Login Functionality
  As an OrangeHRM user
  I want to log in to the application
  So that I can access the HR management system

  Scenario: Successful login with valid admin credentials
    Given the user is on the OrangeHRM login page
    When the user logs in with username "Admin" and password "admin123"
    Then the dashboard should be displayed
    And the user dropdown should be visible in topbar

  Scenario: Login fails with invalid username
    Given the user is on the OrangeHRM login page
    When the user logs in with username "InvalidUser" and password "admin123"
    Then the user should see login error "Invalid credentials"

  Scenario: Login fails with invalid password
    Given the user is on the OrangeHRM login page
    When the user logs in with username "Admin" and password "wrongpass"
    Then the user should see login error "Invalid credentials"

  Scenario: Login fails with empty credentials
    Given the user is on the OrangeHRM login page
    When the user submits login without entering credentials
    Then the required field validation should be displayed

  Scenario: Login page displays OrangeHRM branding
    Given the user is on the OrangeHRM login page
    Then the login page should be visible
    And the OrangeHRM logo should be displayed on login page

  Scenario: Forgot password link is accessible
    Given the user is on the OrangeHRM login page
    When the user clicks forgot password link
    Then the login page should be visible

  Scenario Outline: Login with various invalid credentials
    Given the user is on the OrangeHRM login page
    When the user logs in with username "<username>" and password "<password>"
    Then the user should see login error "Invalid credentials"

    Examples:
      | username | password  |
      | admin    | admin123  |
      | Admin    | Admin123  |
      | ADMIN    | admin123  |
      | test     | test      |
