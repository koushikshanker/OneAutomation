@ui
Feature: Product Page
  As a shopper
  I want to browse and search for products
  So that I can find items to purchase

  Scenario: View product details
    Given the user is on the product page
    When the user selects the product "Sample Product"
    Then the product title should be "Sample Product"

  Scenario: Search for a product
    Given the user is on the product page
    When the user searches for product "Laptop"
    Then the product list should have 1 items

  Scenario: Add product to cart
    Given the user is on the product page
    When the user selects the product "Sample Product"
    And the user adds the product to cart
    Then the cart count should be "1"
