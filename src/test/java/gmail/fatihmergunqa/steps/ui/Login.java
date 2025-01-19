package gmail.fatihmergunqa.steps.ui;

import gmail.fatihmergunqa.utils.Commons;
import gmail.fatihmergunqa.utils.Configs;
import gmail.fatihmergunqa.utils.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login extends Commons {
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        Assert.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
    }

    @Given("I see all fields are present")
    public void iSeeAllFieldsArePresent() {
        Assert.assertTrue(loginPageElements.usernameField.isDisplayed());
        Assert.assertTrue(loginPageElements.passwordField.isDisplayed());
        Assert.assertTrue(loginPageElements.loginButton.isDisplayed());
    }

    @When("I enter username and password")
    public void iEnterUsernameAndPassword() {
        Configs.readProperties(Constants.CONFIGURATION_FILEPATH);

        String username = Configs.getProperty("username");
        String password = Configs.getProperty("password");

        sendText(loginPageElements.usernameField, username);
        sendText(loginPageElements.passwordField, password);
    }

    @And("I click on login button")
    public void iClickOnLoginButton() {
        click(loginPageElements.loginButton);

    }

    @Then("I should be able to login")
    public void iShouldBeAbleToLogin() {
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @When("I enter wrong username or password")
    public void iEnterWrongUsernameOrPassword() {
        sendText(loginPageElements.usernameField, "wrongusername");
        sendText(loginPageElements.passwordField, "wrongpassword");
    }

    @Then("I shouldn't be able to login")
    public void iShouldnTBeAbleToLogin() {
        Assert.assertTrue(loginPageElements.errorMessage.isDisplayed());
        Assert.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        Assert.assertNotEquals("https://www.saucedemo.com/inventory/", driver.getCurrentUrl());
    }
}
