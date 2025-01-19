@Smoke
Feature: Add items to cart and order items functionality

  Background: Navigate to the inventory page
    Given I am on the login page
    When I enter username and password
    And I click on login button
    Then I am on the inventory page

  Scenario: Add all items to cart and checkout
    When I add all items to cart
    And I navigate to cart page
    Given I am on the cart page
    Then I should see all items
    When I click on checkout button
    Then I should be on the checkout page
    When I enter name, password and zip
    And I click on continue button
    Then I should see order details
    When I click on finish button
    Then I should complete checkout

  Scenario: checkout without adding any items
    When I navigate to cart page
    And I click on checkout button
    Then I should not be able to check out