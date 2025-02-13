@Smoke
Feature: Login Functionality

  Background: Navigate to the login page
    Given I am on the login page
    And I see all fields are present

  Scenario: Login with valid credentials
    When I enter username and password
    And I click on login button
    Then I should be able to login

  Scenario: Login with invalid credentials
    When I enter wrong username or password
    And I click on login button
    Then I shouldn't be able to login