package gmail.fatihmergunqa.steps.ui;

import gmail.fatihmergunqa.utils.Commons;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderItems extends Commons {
    List<Map<String, String>> itemsAddedToCart = new ArrayList<>();

    @Then("I am on the inventory page")
    public void iAmOnTheInventoryPage() {
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @When("I add all items to cart")
    public void iAddAllItemsToCart() {
        for (WebElement element : inventoryPageElements.allItems) {
            String itemName = element.findElement(
                    By.xpath(".//div[@class='inventory_item_name ']")).getText().trim();
            String itemDescription = element.findElement(
                    By.xpath(".//div[@class='inventory_item_desc']")).getText().trim();
            String itemPrice = element.findElement(By.xpath(
                    ".//div[@class='inventory_item_price']")).getText().trim();
            WebElement addToCartButton = element.findElement(
                    By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory ']"));

            Map<String, String> itemDetails = new HashMap<>();
            itemDetails.put("itemName", itemName);
            itemDetails.put("itemDescription", itemDescription);
            itemDetails.put("itemPrice", itemPrice);

            itemsAddedToCart.add(itemDetails);

            addToCartButton.click();
        }
    }

    @And("I navigate to cart page")
    public void iNavigateToCartPage() {
        click(inventoryPageElements.cartButton);
    }

    @Given("I am on the cart page")
    public void iAmOnTheCartPage() {
        Assert.assertEquals("https://www.saucedemo.com/cart.html", driver.getCurrentUrl());
    }

    @Then("I should see all items")
    public void iShouldSeeAllItems() {
        for (int i = 0; i < cartPageElements.allItems.size(); i++) {
            WebElement element = cartPageElements.allItems.get(i);

            String cartItemName = element.findElement(
                    By.xpath(".//div[@class='inventory_item_name']")).getText().trim();
            String cartItemDescription = element.findElement(
                    By.xpath(".//div[@class='inventory_item_desc']")).getText().trim();
            String cartItemPrice = element.findElement(By.xpath(
                    ".//div[@class='inventory_item_price']")).getText().trim();


            Map<String, String> expectedItem = itemsAddedToCart.get(i);
            Assert.assertEquals(expectedItem.get("itemName"), cartItemName);
            Assert.assertEquals(expectedItem.get("itemDescription"), cartItemDescription);
            Assert.assertEquals(expectedItem.get("itemPrice"), cartItemPrice);
        }
    }

    @When("I click on checkout button")
    public void iClickOnCheckoutButton() {
        click(cartPageElements.checkoutButton);
    }

    @Then("I should be on the checkout page")
    public void iShouldBeOnTheCheckoutPage() {
        Assert.assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
    }

    @When("I enter name, password and zip")
    public void iEnterNamePasswordAndZip() {
        sendText(checkoutPageElements.nameField, "fatih");
        sendText(checkoutPageElements.lastnameField, "ergun");
        sendText(checkoutPageElements.zipCodeField, "17110");
    }

    @And("I click on continue button")
    public void iClickOnContinueButton() {
        click(checkoutPageElements.continueButton);
    }

    @Then("I should see order details")
    public void iShouldSeeOrderDetails() {
        Assert.assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());

        double expectedOrderSubTotal = 0;
        double actualOrderSubTotal = Double.parseDouble(checkoutPageElements.subTotalLabel.getText().substring(13));

        for (Map<String, String> item : itemsAddedToCart) {
            double itemPrice = Double.parseDouble(item.get("itemPrice").substring(1));
            expectedOrderSubTotal += itemPrice;
        }

        Assert.assertEquals(expectedOrderSubTotal, actualOrderSubTotal, 0.01);
    }

    @When("I click on finish button")
    public void iClickOnFinishButton() {
        click(checkoutPageElements.finishButton);
    }

    @Then("I should complete checkout")
    public void iShouldCompleteCheckout() {
        Assert.assertEquals("https://www.saucedemo.com/checkout-complete.html", driver.getCurrentUrl());
    }

    @Then("I should not be able to check out")
    public void iShouldNotBeAbleToCheckOut() {
        Assert.assertNotEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
    }
}
