@ui
Feature: OrangeHRM Admin User Management
  As an admin
  I want to manage system users
  So that I can control access to the application

  Background:
    Given the user is on the OrangeHRM login page
    When the user logs in with valid admin credentials
    Then the dashboard should be displayed
    When the user navigates to "Admin" from sidebar

  Scenario: System Users page is displayed
    Then the System Users page should be displayed

  Scenario: Search system user by username
    When the user searches by username "Admin"
    Then the admin table should have records
    And the admin records count should be displayed

  Scenario: Filter users by Admin role
    When the user filters by user role "Admin"
    And the user clicks Search on admin page
    Then the admin table should have records

  Scenario: Filter users by ESS role
    When the user filters by user role "ESS"
    And the user clicks Search on admin page
    Then the admin table should have records

  Scenario: Filter users by Enabled status
    When the user filters by status "Enabled"
    And the user clicks Search on admin page
    Then the admin table should have records

  Scenario: Reset user management filters
    When the user filters by user role "Admin"
    And the user clicks Search on admin page
    And the user clicks Reset on admin page
    Then the admin table should have records
