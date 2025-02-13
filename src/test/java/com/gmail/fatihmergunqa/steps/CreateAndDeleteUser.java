package com.gmail.fatihmergunqa.steps;

import com.gmail.fatihmergunqa.testbase.API;
import com.gmail.fatihmergunqa.utils.Commons;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class CreateAndDeleteUser extends Commons {
    @And("I provide the header information")
    public void iProvideTheHeaderInformation() {
        request.header("content-type", "application/json");
    }

    @And("I provide the body information")
    public void iProvideTheBodyInformation(String payload) {
        request.body(payload);
    }

    @And("I make POST request to the {string} endpoint")
    public void iMakePOSTRequestToTheEndpoint(String endpoint) {
        response = request.when().post(API.endpoints.get(endpoint));
    }

    @When("I make PUT request to the {string} endpoint")
    public void iMakePUTRequestToTheEndpoint(String endpoint) {
        response = request.when().put(API.endpoints.get(endpoint));
    }

    @When("I make DELETE request to the {string} endpoint")
    public void iMakeDELETERequestToTheEndpoint(String endpoint) {
        response = request.when().delete(API.endpoints.get(endpoint));
    }
}
