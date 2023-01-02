package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementsActions {
    public WebDriver driver;
    public WebDriverWait wait;
    public ElementsActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(50));
    }

    /**
     * this is a method to wait element and click on it once it become clickable
     *
     * @param locator the By locator of the element to be clicked
     */
    public void clickOn(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    /**
     * this is a method to wait element and enter text to it once it become clickable
     *
     * @param locator the By locator of the element to enter the text to
     * @param text the text to be typed on the element
     */
    public void type(By locator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).clear();
        driver.findElement(locator).sendKeys(text);
    }
    /**
     * this is a method to wait element and get its text
     *
     * @param locator the By locator of the element to get its text
     */
    public String getAnElementText(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }
    /**
     * this is a method to wait until the element exist no more on the page and return false if the element
     * is not displayed anymore
     *
     * @param locator the By locator of the element
     */
    public Boolean isElementStillDisplayed(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return driver.findElements(locator).size() != 0;
    }
}
