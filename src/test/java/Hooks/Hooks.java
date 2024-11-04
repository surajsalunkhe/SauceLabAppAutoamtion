package Hooks;

import com.aventstack.extentreports.Status;
import driver_manager.AndroidDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.AppiumServerUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static context.WebDriverContext.getDriver;
import static driver_manager.AndroidDriverManager.stopRecordingAndroid;
import static report_manager.ExtentManager.extentReports;
import static report_manager.ExtentTestManager.getTest;
import static report_manager.ExtentTestManager.startTest;
//import static utils.AppiumServerUtil.startAppiumServer;
//import static utils.AppiumServerUtil.stopAppiumServer;
import static utils.Constants.configFile;

public class Hooks {
    public Scenario scenario;
    public static Properties properties = new Properties();
    AppiumServerUtil appiumServerUtil=new AppiumServerUtil();

    /*This method is used to initialize the current scenario being executed */
    @Before(order = 0)
    public void scenarioInitialization(Scenario scenario) {
        this.scenario = scenario;
        startTest(scenario.getName(), scenario.getName());
        System.out.println("Scenario initialized");
    }

    /* This method is used to load the properties file */
    @Before(order = 1)
    public void loadPropertiesFile() throws IOException {
        FileInputStream file = new FileInputStream(configFile);
        properties.load(file);
        getTest().log(Status.INFO, "Properties file loaded");
        System.out.println("Properties file loaded");
    }

    @Before(order = 2)
    public void invokeAppium() {
        String system = System.getProperty("os.name").toLowerCase();
        try {
            //stop appium server if existing running
            appiumServerUtil.stopAppiumServer();
            //Start new session of appium
            appiumServerUtil.startAppiumServer(system);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @After(order = 3)
    public void stopRecording() throws IOException {
        if (properties.getProperty("platform").equalsIgnoreCase("android"))
            stopRecordingAndroid(scenario.getName().replaceAll(" ", "_").toLowerCase());
        else if (properties.getProperty("platform").equalsIgnoreCase("ios"))
            stopRecordingAndroid(scenario.getName().replaceAll(" ", "_").toLowerCase());
    }

    @After(order = 2)
    public void takeScreenshotOnFailure() throws IOException {
        if (scenario.isFailed()) {
            AndroidDriverManager.captureLogAndroid("bugreport", scenario.getName().replaceAll(" ", "_").toLowerCase());
            File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshots/"+scenario.getName()+"/screenshot.png"));
            getTest().log(Status.INFO, "Screenshot captured");
        }
    }

    @After(order = 1)
    public void tearDown() throws IOException {
        AndroidDriverManager.captureLogAndroid("logcat", scenario.getName().replaceAll(" ", "_").toLowerCase());
        getDriver().quit();
        getTest().log(Status.INFO, "Driver quit successfully");
    }
    @After(order = 0)
    public void stopAppium() {
        appiumServerUtil.stopAppiumServer();
        extentReports.flush();
    }
}
