import actions.ReportActions;
import com.shaft.tools.io.JSONFileManager;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckoutTest {
    private JSONFileManager jsonLoginFile;
    private JSONFileManager jsonCheckoutFile;
    private WebDriver driver;
    /**
     * this before method includes the capabilities to be sent to Appium server and the precondition as
     * the login page will load after the server response to the requested capabilities
     * iy also contains json data manager object instance
     * @throws MalformedURLException it will be thrown when the url is written in incorrect way
     *
     */
    @BeforeClass
    public void setUp() throws MalformedURLException {
        jsonLoginFile = new JSONFileManager("src/test/resources/testDataFiles/jsonDataFiles/LoginData.json");
        jsonCheckoutFile = new JSONFileManager("src/test/resources/testDataFiles/jsonDataFiles/Checkout.json");
        String AppName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataFiles\\Android.SauceLabs.Mobile.Sample.app.2.2.0.apk";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Android Emulator");
        caps.setCapability("appium:app", AppName);
        caps.setCapability("appium:platformVersion", "11.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appWaitActivity", "com.swaglabsmobileapp.MainActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);

        //login as a precondition
        new LoginPage(driver).login(jsonLoginFile.getTestData("username"), jsonLoginFile.getTestData("password"));

    }

    /**
     * the following test validate the end-to-end scenario from adding item to cart till checking out and
     * the purchase is completed successfully
     */
    @Test
    public void checkCheckoutFunctionality() {
        String pdtPrice =
                new ProductsPage(driver)
                .clickOnAddToCartBTN().getFirstProductPrice();
        String pdtPriceInCheckOutOverView =
                new ProductsPage(driver)    .clickOnCartIcon()
                .clickOnCheckOut()
                .enterFirstName(jsonCheckoutFile.getTestData("firstName"))
                .enterLastName(jsonCheckoutFile.getTestData("lastname"))
                .enterZipCode(jsonCheckoutFile.getTestData("zipCode"))
                .clickOnContinueBTN()
                .getProductPrice();
        new CheckOutOverviewPage(driver).clickOnFinishBTN();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(pdtPrice, pdtPriceInCheckOutOverView);
        softAssert.assertTrue(new CheckOutCompletePage(driver).getCheckOutCompleteLabel().contains("CHECKOUT: COMPLETE!"));
        softAssert.assertAll();
    }
    @AfterMethod
    public void takeScreenShot(ITestResult result) throws FileNotFoundException {
        // take screenshot
        File screenShot = ReportActions.takeScreenShot(driver, result.getName());
        // attach the screenshot to the report
        Allure.addAttachment(result.getTestName(), new FileInputStream(screenShot));
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
