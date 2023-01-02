package pages;

import actions.ElementsActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private By PRODUCTS = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
    private By ADD_TO_CART_BTN = By.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView");
    private By FIRST_PRODUCT_Name = By.xpath("(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]");
    private By FIRST_PRODUCT_PRICE = By.xpath("(//android.widget.TextView[@content-desc=\"test-Price\"])[1]");
    private By CART_ICON = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView");
    private ElementsActions elementsActions;
    private WebDriver driver;
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        elementsActions = new ElementsActions(driver);
    }
    /**
     * this method to click on the cart icon in the top right of the page
     * @return the Cart page
     */
    public CartPage clickOnCartIcon() {
        elementsActions.clickOn(CART_ICON);
        return new CartPage(driver);
    }
    public String getProductsTitleText() {
        return elementsActions.getAnElementText(PRODUCTS);
    }
    public ProductsPage clickOnAddToCartBTN() {
        elementsActions.clickOn(ADD_TO_CART_BTN);
        return this;
    }
    public String getFirstProductName() {
        return elementsActions.getAnElementText(FIRST_PRODUCT_Name);
    }
    public String getFirstProductPrice() {
        return elementsActions.getAnElementText(FIRST_PRODUCT_PRICE);
    }



}
