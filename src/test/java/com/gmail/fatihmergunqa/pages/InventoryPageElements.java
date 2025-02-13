package com.gmail.fatihmergunqa.pages;

import com.gmail.fatihmergunqa.testbase.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPageElements extends Base {
    @FindBy(id = "shopping_cart_container")
    public WebElement cartButton;

    @FindBy(xpath = "//div[@class='inventory_item']")
    public List<WebElement> allItems;

    public InventoryPageElements() {
        PageFactory.initElements(Base.driver, this);
    }
}
