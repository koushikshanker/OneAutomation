@ui
Feature: Checkout Process
  As a shopper
  I want to complete the checkout process
  So that I can purchase my selected items

  Scenario: View cart summary
    Given the user is on the product page
    When the user selects the product "Sample Product"
    And the user adds the product to cart
    Then the cart summary should be displayed

  Scenario: Complete checkout
    Given the user is on the product page
    When the user selects the product "Sample Product"
    And the user adds the product to cart
    And the user proceeds to checkout
    Then the order confirmation should be displayed
