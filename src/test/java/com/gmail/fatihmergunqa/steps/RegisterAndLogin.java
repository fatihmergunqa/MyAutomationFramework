package com.gmail.fatihmergunqa.steps;

import com.gmail.fatihmergunqa.utils.Commons;
import com.gmail.fatihmergunqa.utils.Configs;
import com.gmail.fatihmergunqa.utils.Constants;
import io.cucumber.java.en.And;
import org.hamcrest.Matchers;

public class RegisterAndLogin extends Commons {
    @And("I update framework token")
    public void iUpdateFrameworkToken() {
        String token = response.then().extract().body().jsonPath().getString("token");

        Configs.setProperty(
                Constants.CONFIGURATION_FILEPATH,
                "api_token",
                token
        );
    }

    @And("I validate that response body contains {string}")
    public void iValidateThatResponseBodyContains(String path) {
        response.then().assertThat().body(path, Matchers.notNullValue());
    }
}
