@ui
Feature: OrangeHRM Apply Leave
  As an employee
  I want to apply for leave
  So that I can take time off from work

  Background:
    Given the user is on the OrangeHRM login page
    When the user logs in with valid admin credentials
    Then the dashboard should be displayed
    When the user navigates to "Leave" from sidebar

  Scenario: Apply Leave page is accessible
    When the user clicks topbar tab "Apply"
    Then the Apply Leave page should be displayed

  Scenario: View My Leave list
    When the user clicks topbar tab "My Leave"
    Then the leave list table should be displayed
