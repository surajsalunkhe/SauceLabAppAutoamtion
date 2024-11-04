package driver_manager;

import com.aventstack.extentreports.Status;
import driver.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static Hooks.Hooks.properties;
import static context.WebDriverContext.getDriver;
import static report_manager.ExtentTestManager.getTest;
import static utils.Constants.*;

public class AndroidDriverManager implements Driver {
    private AndroidDriver driver;

    // This method is used to create an android driver instance with set of desired capabilities
    @Override
    public AppiumDriver createInstance() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = buildAndroidCapabilities();
        driver = new AndroidDriver(new URL("http://" + HOST + ":" + PORT_FOR_ANDROID), desiredCapabilities);
        driver.startRecordingScreen();
        getTest().log(Status.INFO, "Android driver is created");
        System.out.println("Android driver is created");
        return driver;
    }
    private DesiredCapabilities buildAndroidCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", properties.getProperty("AndroidEmulator"));
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:app", androidAppPath);
        desiredCapabilities.setCapability("appium:appPackage", properties.getProperty("appPackage"));
        desiredCapabilities.setCapability("appium:appActivity", properties.getProperty("appActivity"));
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:noReset", false);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        return desiredCapabilities;
    }

    // Stop video recording of Android
    public static void stopRecordingAndroid(String scenarioName) throws IOException {
        String video = ((AndroidDriver)getDriver()).stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(video);
        FileUtils.writeByteArrayToFile(new File("target/videos/"+scenarioName+"/ScenarioRecording.mp4"), decode);
        getTest().log(Status.INFO, "Video recording stopped");
    }

    //Capture logs for Android
    public static void captureLogAndroid(String logType, String scenarioName) throws IOException {
        DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH-mm-ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        List<LogEntry> logEntries = getDriver().manage().logs().get(logType).getAll();
        FileUtils.writeLines(new File("target/logs/" + logType + "/" +scenarioName+ "/" + reportDate + ".txt"), logEntries);
        if(properties.getProperty("AppiumConsoleOutputLog").equalsIgnoreCase("Y")){
            logEntries.forEach(System.out::println);
        }
        getTest().log(Status.INFO, logType.toUpperCase() + " logs captured");
        System.out.println(logType.toUpperCase() + " logs captured");
    }
}
