package pages;

import actions.ElementsActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutCompletePage {
    private By CHECKOUT_COMPLETE_LABEL = By.xpath("//android.widget.TextView[@text=\"CHECKOUT: COMPLETE!\"]");
    private ElementsActions elementsActions;
    private WebDriver driver;
    public CheckOutCompletePage(WebDriver driver) {
        this.driver = driver;
        elementsActions = new ElementsActions(driver);
    }

    /**
     * the following method is to get the text of the page Header to make sure that we are landing on the right page
     * @return CHECKOUT: COMPLETED ->   the header of the page we are landing on
     */
    public String getCheckOutCompleteLabel() {
        return elementsActions.getAnElementText(CHECKOUT_COMPLETE_LABEL);
    }
}
