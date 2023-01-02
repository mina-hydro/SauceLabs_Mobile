package pages;

import actions.ElementsActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutInformationPage {
    private By FIRST_NAME_TEXT_BOX = AppiumBy.accessibilityId("test-First Name");
    private By LAST_NAME_TEXT_BOX = AppiumBy.accessibilityId("test-Last Name");
    private By ZIP_CODE_TEXT_BOX = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private By CONTINUE_BTN = AppiumBy.accessibilityId("test-CONTINUE");
    private ElementsActions elementsActions;
    private WebDriver driver;
    public CheckOutInformationPage(WebDriver driver) {
        this.driver = driver;
        elementsActions = new ElementsActions(driver);
    }

    /**
     * the following methods are to complete the checkout: information process
     *
     */
    public CheckOutInformationPage enterFirstName(String firstName) {
        elementsActions.type(FIRST_NAME_TEXT_BOX, firstName);
        return this;
    }
    public CheckOutInformationPage enterLastName(String lastname) {
        elementsActions.type(LAST_NAME_TEXT_BOX, lastname);
        return this;
    }
    public CheckOutInformationPage enterZipCode(String zipCode) {
        elementsActions.type(ZIP_CODE_TEXT_BOX, zipCode);
        return this;
    }
    public CheckOutOverviewPage clickOnContinueBTN() {
        elementsActions.clickOn(CONTINUE_BTN);
        return new CheckOutOverviewPage(driver);
    }
}
