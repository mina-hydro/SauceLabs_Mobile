import actions.ReportActions;
import com.shaft.tools.io.JSONFileManager;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public class AddAndRemoveFromCartTest {
    // json file manager object to read test data from json file
    private JSONFileManager jsonLoginFile;
    private WebDriver driver;
    /**
     * this before method includes the capabilities to be sent to Appium server and the precondition as
     * the login page will load after the server response to the requested capabilities
     * it also contains json data manager object instance
     * @throws MalformedURLException it will be thrown when the url is written in incorrect way
     *
     */
    @BeforeClass
    public void setUp() throws MalformedURLException {
        jsonLoginFile = new JSONFileManager("src/test/resources/testDataFiles/jsonDataFiles/LoginData.json");
        String AppName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataFiles\\Android.SauceLabs.Mobile.Sample.app.2.2.0.apk";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Android Emulator");
        caps.setCapability("appium:app", AppName);
        caps.setCapability("appium:platformVersion", "11.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appWaitActivity", "com.swaglabsmobileapp.MainActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);

        // preconditions
        new LoginPage(driver).login(jsonLoginFile.getTestData("username"), jsonLoginFile.getTestData("password"));
    }

    @Test
    public void testAddToCatFunctionality(){
        String pdtName = new ProductsPage(driver).getFirstProductName();
        String pdtPrice = new ProductsPage(driver).getFirstProductPrice();
        CartPage cartPage = new ProductsPage(driver).clickOnAddToCartBTN().clickOnCartIcon();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(pdtName, cartPage.getFirstProductName());
        softAssert.assertEquals(pdtPrice, cartPage.getFirstProductPrice());
    }

    @Test
    public void testRemovingItemFromCartAndCartIsEmpty() {
        new ProductsPage(driver).clickOnCartIcon().clickOnRemoveBTN();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(new CartPage(driver).isFirstItemStillExist());
        softAssert.assertFalse(new CartPage(driver).isItemsNumberOnCartIconDisplayed());
    }

    /** the following BeforeMethod is to get back to products page after each test as
     * this website doesn't preserve the data of the session after logging out
     *   ------ also it has methods to take screenshots and attach it to the report
         */

    @AfterMethod
    public void backToProductsPage(ITestResult result) throws FileNotFoundException {
        // take screenshot
        File screenShot = ReportActions.takeScreenShot(driver, result.getName());
        // attach the screenshot to the report
        Allure.addAttachment(result.getTestName(), new FileInputStream(screenShot));
        new CartPage(driver).clickOnContinueShopping();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
