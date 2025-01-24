package gmail.fatihmergunqa.steps.api;

import gmail.fatihmergunqa.utils.Commons;
import gmail.fatihmergunqa.utils.Configs;
import gmail.fatihmergunqa.utils.Constants;
import io.cucumber.java.en.And;

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
}
