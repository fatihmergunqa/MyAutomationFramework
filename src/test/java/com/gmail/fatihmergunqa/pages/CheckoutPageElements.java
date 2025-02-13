package com.gmail.fatihmergunqa.pages;

import com.gmail.fatihmergunqa.testbase.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageElements extends Base {
    @FindBy(id = "first-name")
    public WebElement nameField;

    @FindBy(id = "last-name")
    public WebElement lastnameField;

    @FindBy(id = "postal-code")
    public WebElement zipCodeField;

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement errorMessage;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(xpath = "//div[@class='cart_item']")
    public WebElement allItems;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    public WebElement subTotalLabel;

    @FindBy(id = "finish")
    public WebElement finishButton;

    public CheckoutPageElements() {
        PageFactory.initElements(Base.driver, this);
    }
}
