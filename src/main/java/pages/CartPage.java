package pages;

import actions.ElementsActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    // the page locators
    private By FIRST_PRODUCT_NAME = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");
    private By FIRST_PRODUCT_PRICE = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView");
    private By REMOVE_BTN = By.xpath("//android.widget.TextView[@text=\"REMOVE\"]");
    private By CONTINUE_SHOPPING_BTN = By.xpath("//android.widget.TextView[@text=\"CONTINUE SHOPPING\"]");
    private By ITEMS_NUMBER_ON_CART_ICON = By.xpath( "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.TextView");
    private By CHECK_OUT_BTN = By.xpath("//android.widget.TextView[@text=\"CHECKOUT\"]");

    //object of the framework to help in code redundancy
    private ElementsActions elementsActions;
    private WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        elementsActions = new ElementsActions(driver);
    }

    /**
     * the following methods are implemented using the ElementsActions class as a framework
     *
     */
    public String getFirstProductName() {
        return elementsActions.getAnElementText(FIRST_PRODUCT_NAME);
    }
    public String getFirstProductPrice() {
        return elementsActions.getAnElementText(FIRST_PRODUCT_PRICE);
    }
    public CartPage clickOnRemoveBTN() {
        elementsActions.clickOn(REMOVE_BTN);
        return this;
    }

    /**
     * this method use findElements method
     * @return false if the size of the list
     *        of elements is zero as this means the element exists no more
     */
    public Boolean isItemsNumberOnCartIconDisplayed() {
        return elementsActions.isElementStillDisplayed(ITEMS_NUMBER_ON_CART_ICON);
    }
    public CheckOutInformationPage clickOnCheckOut() {
        elementsActions.clickOn(CHECK_OUT_BTN);
        return new CheckOutInformationPage(driver);
    }
    /**
     * this method use findElements method
     * @return false if the size of the list
     *        of elements is zero as this means the element exists no more
     */
    public Boolean isFirstItemStillExist() {
        return elementsActions.isElementStillDisplayed(FIRST_PRODUCT_NAME);
    }
    public ProductsPage clickOnContinueShopping() {
        elementsActions.clickOn(CONTINUE_SHOPPING_BTN);
        return new ProductsPage(driver);
    }
}
