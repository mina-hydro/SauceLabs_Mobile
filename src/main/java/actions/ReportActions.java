package actions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ReportActions {
    /**
     * this method takes screenshot during the test
     * @param testName the name of the test during which the screenshot took
     */
    public static File takeScreenShot(WebDriver driver, String testName) {
        File driverScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenShot = new File("src/main/screenShots/" + testName + ".png");
        try {
            Files.move(driverScreenShot.toPath(), screenShot.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenShot;
    }
}
