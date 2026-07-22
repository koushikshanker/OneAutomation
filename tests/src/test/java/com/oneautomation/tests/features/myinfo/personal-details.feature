@ui
Feature: OrangeHRM My Info Personal Details
  As an employee
  I want to view and update my personal details
  So that my profile information is current

  Background:
    Given the user is on the OrangeHRM login page
    When the user logs in with valid admin credentials
    Then the dashboard should be displayed
    When the user navigates to "My Info" from sidebar

  Scenario: Personal Details page is displayed
    Then the Personal Details page should be displayed

  Scenario: First name is pre-populated
    Then the first name field should not be empty

  Scenario: Last name is pre-populated
    Then the last name field should not be empty

  Scenario: Profile image is displayed
    Then the profile image should be displayed

  Scenario: Update middle name
    When the user updates middle name to "TestMiddle"
    And the user clicks Save on personal details
    Then the personal details toast should be displayed

  Scenario: Navigate to Contact Details tab
    When the user navigates to Contact Details tab
    Then the page title should be "PIM"
