package driver_manager;

import com.aventstack.extentreports.Status;
import driver.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static Hooks.Hooks.properties;
import static report_manager.ExtentTestManager.getTest;
import static utils.Constants.*;

public class IOSDriverManager implements Driver {
    private IOSDriver driver;


    @Override
    public AppiumDriver createInstance() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = buildIOSCapabilities();
        driver = new IOSDriver(new URL("http://" + HOST + ":" + PORT_FOR_IOS + "/wd/hub"), desiredCapabilities);
        driver.startRecordingScreen();
        getTest().log(Status.INFO, "IOS driver is created");
        System.out.println("IOS driver is created");
        return driver;
    }
    private DesiredCapabilities buildIOSCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", "iPhone 13");
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("appium:automationName", "XCUITest");
        desiredCapabilities.setCapability("appium:noReset", true);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        return desiredCapabilities;
    }
}
