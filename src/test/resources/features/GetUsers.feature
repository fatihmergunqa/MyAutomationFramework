@Smoke @API
Feature: GET user(s) functionality

  Background: Create a request
    Given I created a request

  Scenario: Get all users
    When I provide path "page" value "1" as a query parameter
    And I make a GET request to the "GET_LIST_USERS" endpoint
    Then I validate that the status code is 200
    And I validate that response body "page" value is 1
    And I validate that response body "per_page" value is 6
    And I validate that response body "size()" value is 6

  Scenario: Get single user
    When I provide "2" as a path parameter
    And I make a GET request to the "GET_SINGLE_USER" endpoint
    Then I validate that the status code is 200
    And I validate that response body "data.id" value is 2
    And I validate that response body "data.email" value is "janet.weaver@reqres.in"

  Scenario: Get single user not found
    When I provide "21928383" as a path parameter
    And I make a GET request to the "GET_SINGLE_USER" endpoint
    And I validate that the status code is 404
    Then I validate that response body is empty