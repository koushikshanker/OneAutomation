@ui
Feature: OrangeHRM PIM Employee Management
  As an HR admin
  I want to manage employees in the PIM module
  So that I can maintain employee records

  Background:
    Given the user is on the OrangeHRM login page
    When the user logs in with valid admin credentials
    Then the dashboard should be displayed
    When the user navigates to "PIM" from sidebar

  Scenario: Employee list displays records
    Then the employee table should have records
    And the records count text should be displayed

  Scenario: Add a new employee with basic details
    When the user clicks Add Employee button
    And the user adds employee with first name "John" and last name "Automation"
    Then the success toast should be displayed

  Scenario: Add a new employee with full name
    When the user clicks Add Employee button
    And the user adds employee with name "Jane" "M" "Automation"
    Then the success toast should be displayed

  Scenario: Search employee by name
    When the user searches employee by name "Admin"
    Then the employee table should have records

  Scenario: Search with non-existent employee name
    When the user searches employee by name "ZZZNONEXISTENT"
    Then no records found should be displayed

  Scenario: Reset search filters
    When the user searches employee by name "Admin"
    And the user clicks Reset on employee list
    Then the employee table should have records

  Scenario: Edit first employee in list
    When the user clicks edit on first employee
    Then the Personal Details page should be displayed
