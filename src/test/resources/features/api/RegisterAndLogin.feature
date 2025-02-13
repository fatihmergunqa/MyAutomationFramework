@Smoke @API
Feature: Register and login functionality

  Background:
    Given I created a request
    And I provide the header information

  Scenario: Successful registration
    When I provide the body information
    """
    {
      "email": "eve.holt@reqres.in",
      "password": "pistol"
    }
    """
    And I make POST request to the "POST_REGISTER" endpoint
    And I validate that the status code is 200
    And I validate that response body "id" value is 4
    And I validate that response body contains "token"
    And I update framework token

  Scenario: Unsuccessful registration
    When I provide the body information
    """
    {
      "email": "eve.holt@reqres.in"
    }
    """
    And I make POST request to the "POST_REGISTER" endpoint
    And I validate that the status code is 400
    And I validate that response body "error" value is "Missing password"

  Scenario: Successful login
    When I provide the body information
    """
    {
      "email": "eve.holt@reqres.in",
      "password": "pistol"
    }
    """
    And I make POST request to the "POST_LOGIN" endpoint
    And I validate that the status code is 200
    And I validate that response body contains "token"
    And I update framework token

  Scenario: Unsuccessful login
    When I provide the body information
    """
    {
      "email": "eve.holt@reqres.in"
    }
    """
    And I make POST request to the "POST_LOGIN" endpoint
    And I validate that the status code is 400
    And I validate that response body "error" value is "Missing password"