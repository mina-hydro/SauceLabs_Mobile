import actions.ReportActions;
import com.shaft.tools.io.JSONFileManager;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {
    private JSONFileManager jsonLoginFile;
    private WebDriver driver;

    /**
     * this before method includes the capabilities to be sent to Appium server and the precondition as
     * the login page will load after the server response to the requested capabilities
     * iy also contains json data manager object instance
     * @throws MalformedURLException it will be thrown when the url is written in incorrect way
     *
     */

    @BeforeMethod
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
    }
    @Test
    public void testSuccessfulLogin() {
        ProductsPage productsPage = new LoginPage(driver)
                .enterUserName(jsonLoginFile.getTestData("username"))
                .enterPassword(jsonLoginFile.getTestData("password"))
                .clickOnLoginBTN();
        Assert.assertTrue(productsPage.getProductsTitleText().contains("PRODUCTS"));
    }
    @Test
    public void testUnsuccessfulLogin() {
        new LoginPage(driver)
                .enterUserName(jsonLoginFile.getTestData("username"))
                .enterPassword(jsonLoginFile.getTestData("invalidPassword"))
                .clickOnLoginBTN();
        Assert.assertTrue(new LoginPage(driver).getUnsuccessfulLoginMessageText().contains("Username and password do not match any user in this service"));

    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        // take screenshot
        File screenShot = ReportActions.takeScreenShot(driver, result.getName());
        // attach the screenshot to the report
        Allure.addAttachment(result.getTestName(), new FileInputStream(screenShot));
        if (driver != null) driver.quit();
    }
}
