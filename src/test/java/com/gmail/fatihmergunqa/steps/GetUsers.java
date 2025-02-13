package com.gmail.fatihmergunqa.steps;

import com.gmail.fatihmergunqa.testbase.API;
import com.gmail.fatihmergunqa.utils.Commons;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class GetUsers extends Commons {
    @Given("I created a request")
    public void iCreatedARequest() {
        RestAssured.baseURI = BASE_URI;
        request = RestAssured.given();
    }

    @When("I provide path {string} value {string} as a query parameter")
    public void iProvidePathValueAsAQueryParameter(String parameterName, String parameterValue) {
        request.queryParam(parameterName, parameterValue);
    }

    @When("I provide {string} as a path parameter")
    public void iProvideAsAPathParameter(String parameterValue) {
        request.pathParams("id", parameterValue);
    }

    @And("I make a GET request to the {string} endpoint")
    public void iMakeAGETRequestToTheEndpoint(String endpoint) {
        response = request.when().get(API.endpoints.get(endpoint));
    }

    @Then("I validate that the status code is {int}")
    public void iValidateThatTheStatusCodeIs(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @And("I validate that response body {string} value is {int}")
    public void iValidateThatValueIs(String path, int value) {
        response.then().assertThat().body(path, Matchers.is(value));
    }

    @And("I validate that response body {string} value is {string}")
    public void iValidateThatResponseBodyValueIs(String path, String value) {
        response.then().assertThat().body(path, Matchers.is(value));
    }

    @Then("I validate that response body is empty")
    public void iValidateThatResponseBodyIsEmpty() {
        Assert.assertTrue(response.jsonPath().getMap("$").isEmpty());
    }
}
