@ui
Feature: OrangeHRM Dashboard
  As a logged in OrangeHRM user
  I want to view the dashboard
  So that I can see key HR metrics at a glance

  Background:
    Given the user is on the OrangeHRM login page
    When the user logs in with valid admin credentials
    Then the dashboard should be displayed

  Scenario: Dashboard grid loads after login
    Then the dashboard grid should be loaded
    And the dashboard URL should contain "dashboard"

  Scenario: Time at Work widget is visible
    Then the Time at Work widget should be displayed

  Scenario: Employees on Leave widget is visible
    Then the Employees on Leave widget should be displayed

  Scenario: Quick launch section has items
    Then the quick launch section should have items

  Scenario: Navigate to Assign Leave from quick launch
    When the user clicks on quick launch "Assign Leave"
    Then the page title should be "Leave"

  Scenario: Navigate to Leave List from quick launch
    When the user clicks on quick launch "Leave List"
    Then the page title should be "Leave"

  Scenario: Navigate to Timesheets from quick launch
    When the user clicks on quick launch "Timesheets"
    Then the page title should be "Time"
