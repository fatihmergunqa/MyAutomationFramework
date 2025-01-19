package gmail.fatihmergunqa.pages;

import gmail.fatihmergunqa.testbase.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageElements {
    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@class = 'error-message-container error']")
    public WebElement errorMessage;

    public LoginPageElements() {
        PageFactory.initElements(Base.driver, this);
    }
}
