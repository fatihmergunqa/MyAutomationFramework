package com.gmail.fatihmergunqa.pages;

import com.gmail.fatihmergunqa.testbase.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPageElements extends Base {
    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy(xpath = "//div[@class='cart_item']")
    public List<WebElement> allItems;

    public CartPageElements() {
        PageFactory.initElements(Base.driver, this);
    }
}
