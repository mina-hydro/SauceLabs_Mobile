package pages;

import actions.ElementsActions;
import actions.MobileActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutOverviewPage {
    private By FINISH_BTN = AppiumBy.accessibilityId("test-FINISH");
    private By PRODUCT_PRICE_LABEL = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView");
    private ElementsActions elementsActions;
    private WebDriver driver;
    public CheckOutOverviewPage(WebDriver driver) {
        this.driver = driver;
        elementsActions = new ElementsActions(driver);
        new MobileActions(driver);
    }

    /**
     * this method to scroll down and click finish
     * @return CheckOutCompletePage ->  the next page
     */
    public CheckOutCompletePage clickOnFinishBTN() {
        MobileActions.scrollDownToSpecificText("FINISH");
        elementsActions.clickOn(FINISH_BTN);
        return new CheckOutCompletePage(driver);
    }
    public String getProductPrice() {
        return elementsActions.getAnElementText(PRODUCT_PRICE_LABEL);
    }
}
