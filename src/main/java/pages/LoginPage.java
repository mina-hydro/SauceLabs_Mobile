package pages;

import actions.ElementsActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private By USER_NAME_TEXT_BOX = AppiumBy.accessibilityId("test-Username");
    private By PASSWORD_TEXT_BOX = AppiumBy.accessibilityId("test-Password");
    private By LOGIN_BTN = AppiumBy.accessibilityId("test-LOGIN");
    private By UNSUCCESSFUL_LOGIN_MESSAGE = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
    private ElementsActions elementsActions;
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementsActions = new ElementsActions(driver);
    }

    /*
     * the following methods to login
     *
     */
    public LoginPage enterUserName(String username) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(USER_NAME_TEXT_BOX)).sendKeys(username);
        return this;
    }
    public LoginPage enterPassword(String password) {
        driver.findElement(PASSWORD_TEXT_BOX).sendKeys(password);
        return this;
    }
    public ProductsPage clickOnLoginBTN() {
        elementsActions.clickOn(LOGIN_BTN);
        return new ProductsPage(driver);
    }
    /*this method to login in one step in preconditions

     */
    public ProductsPage login(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickOnLoginBTN();
        return new ProductsPage(driver);
    }

    /**
     * this method to assert the "unsuccessful login" message has appeared
     * @return the text in the "unsuccessful login message"
     */
    public String getUnsuccessfulLoginMessageText() {
        return driver.findElement(UNSUCCESSFUL_LOGIN_MESSAGE).getText();
    }
}
