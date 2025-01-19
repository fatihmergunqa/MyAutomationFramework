package gmail.fatihmergunqa.testbase;

import gmail.fatihmergunqa.pages.CartPageElements;
import gmail.fatihmergunqa.pages.CheckoutPageElements;
import gmail.fatihmergunqa.pages.InventoryPageElements;
import gmail.fatihmergunqa.pages.LoginPageElements;

/**
 * PageInitializer class responsible for initializing page element objects.
 * It extends the Base class to inherit WebDriver setup and teardown functionality.
 */
public class PageInitializer extends Base {
    // Page element instances
    public static LoginPageElements loginPageElements;
    public static InventoryPageElements inventoryPageElements;
    public static CartPageElements cartPageElements;
    public static CheckoutPageElements checkoutPageElements;

    /**
     * Initializes the page elements by creating new instances of the corresponding page element classes.
     */
    public static void initialize() {
        loginPageElements = new LoginPageElements();
        inventoryPageElements = new InventoryPageElements();
        cartPageElements = new CartPageElements();
        checkoutPageElements = new CheckoutPageElements();
    }
}