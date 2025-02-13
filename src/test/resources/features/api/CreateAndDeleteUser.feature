@Smoke @API
Feature: Create, update, and delete user

  Background: Create a request
    Given I created a request
    And I provide the header information

  Scenario: Create a user
    When I provide the body information
    """
    {
      "name": "morpheus",
      "job": "leader"
    }
    """
    And I make POST request to the "POST_CREATE_USER" endpoint
    And I validate that the status code is 201
    And I validate that response body "name" value is "morpheus"
    And I validate that response body "job" value is "leader"

  Scenario: Update a user
    When I provide the body information
    """
    {
      "name": "morpheus",
      "job": "zion resident"
    }
    """
    When I make PUT request to the "PUT_UPDATE_USER" endpoint
    And I validate that the status code is 200
    And I validate that response body "name" value is "morpheus"
    And I validate that response body "job" value is "zion resident"

  Scenario: Delete a user
    When I make DELETE request to the "DELETE_USER" endpoint
    And I validate that the status code is 204