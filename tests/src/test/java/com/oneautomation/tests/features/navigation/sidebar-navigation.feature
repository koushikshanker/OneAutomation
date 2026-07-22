@ui
Feature: OrangeHRM Sidebar Navigation
  As a logged in OrangeHRM user
  I want to navigate using the sidebar menu
  So that I can access different modules of the application

  Background:
    Given the user is on the OrangeHRM login page
    When the user logs in with valid admin credentials
    Then the dashboard should be displayed

  Scenario: Sidebar is visible after login
    Then the sidebar should be visible

  Scenario: Navigate to Admin module
    When the user navigates to "Admin" from sidebar
    Then the System Users page should be displayed

  Scenario: Navigate to PIM module
    When the user navigates to "PIM" from sidebar
    Then the page title should be "PIM"

  Scenario: Navigate to Leave module
    When the user navigates to "Leave" from sidebar
    Then the page title should be "Leave"

  Scenario: Navigate to My Info module
    When the user navigates to "My Info" from sidebar
    Then the Personal Details page should be displayed

  Scenario: Navigate to Directory module
    When the user navigates to "Directory" from sidebar
    Then the page title should be "Directory"

  Scenario: Navigate to Buzz module
    When the user navigates to "Buzz" from sidebar
    Then the page title should be "Buzz"

  Scenario: Logout from user dropdown
    When the user logs out
    Then the user should be redirected to login page

  Scenario: Toggle sidebar collapse
    When the user toggles sidebar collapse
    Then the sidebar should be visible
