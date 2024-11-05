package utils;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

import java.util.Set;

import static report_manager.ExtentTestManager.getTest;

public class SwitchContextUtil {

    /*public static void changeDriverContext(WebDriver driver, String contextType, String platform) {
        switch (platform.toLowerCase()) {
            case "android":
                Set<String> contextNamesAndroid = ((AndroidDriver)driver).getContextHandles();
                for (String contextName : contextNamesAndroid) {
                    if (contextName.contains(contextType))
                        ((AndroidDriver) driver).context(contextName);
                }
                break;

            case "ios":
                Set<String> contextNamesIOS = ((IOSDriver)driver).getContextHandles();
                for (String contextName : contextNamesIOS) {
                    if (contextName.contains(contextType))
                        ((IOSDriver) driver).context(contextName);
                }
                break;
        }
        getTest().log(Status.PASS, "Changed '" + platform + "' driver context to "+contextType);
    }*/
    public static void changeDriverContext(WebDriver driver, String contextType, String platform) {
        Set<String> contextNames;

        switch (platform.toLowerCase()) {
            case "android":
                if (driver instanceof AndroidDriver) {
                    contextNames = ((AndroidDriver) driver).getContextHandles();
                    for (String contextName : contextNames) {
                        if (contextName.contains(contextType)) {
                            ((AndroidDriver) driver).context(contextName);
                            getTest().log(Status.PASS, "Changed 'android' driver context to " + contextType);
                            return;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Driver is not an instance of AndroidDriver.");
                }
                break;

            case "ios":
                if (driver instanceof IOSDriver) {
                    contextNames = ((IOSDriver) driver).getContextHandles();
                    for (String contextName : contextNames) {
                        if (contextName.contains(contextType)) {
                            ((IOSDriver) driver).context(contextName);
                            getTest().log(Status.PASS, "Changed 'ios' driver context to " + contextType);
                            return;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Driver is not an instance of IOSDriver.");
                }
                break;

            default:
                throw new IllegalArgumentException("Unsupported platform: " + platform);
        }

        throw new IllegalStateException("Failed to change driver context to " + contextType);
    }
}
