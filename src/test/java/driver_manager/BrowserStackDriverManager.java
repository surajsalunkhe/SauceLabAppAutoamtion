package driver_manager;

import driver.Driver;
import com.browserstack.local.Local;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static Hooks.Hooks.properties;

import java.net.MalformedURLException;

public class BrowserStackDriverManager implements Driver {


    private static final String BROWSERSTACK_USERNAME = properties.getProperty("browserstack.username");
    private static final String BROWSERSTACK_ACCESS_KEY = properties.getProperty("browserstack.accessKey");

    private Local local;

    public static HashMap<String,Object> getCommonBsOptions(){
        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", "prathamesh_qZuHcW");
        bstackOptions.put("accessKey", "NitDT7PTzzpBRaqwFjcs");
        if (properties.getProperty("platform").equalsIgnoreCase("android")) {
        bstackOptions.put("appiumVersion", "2.6.0");
        }else{
            bstackOptions.put("appiumVersion", "2.0.1");
        }
        return bstackOptions;
    }
    /**
     * @return
     */
    @Override
    public AppiumDriver createInstance() {
        DesiredCapabilities caps = new DesiredCapabilities();
        // Set device-specific capabilities
        if (properties.getProperty("platform").equalsIgnoreCase("android")) {
            //caps.setCapability("device", properties.getProperty("browserstack.android.device"));
            //caps.setCapability("os_version", properties.getProperty("browserstack.android.version"));
            caps.setCapability("platformName", "android");
            caps.setCapability("appium:platformVersion", "12.0");
            caps.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
            caps.setCapability("appium:app", "bs://891cc0565af3aaa88147c7de29eca66e18a62ee3");
        } else if (properties.getProperty("platform").equalsIgnoreCase("ios")) {
            caps.setCapability("platformName", "ios");
            caps.setCapability("appium:platformVersion", "14.0");
            caps.setCapability("appium:deviceName", "iPhone 12 Pro Max");
            //caps.setCapability("device", properties.getProperty("browserstack.ios.device"));
            //caps.setCapability("os_version", properties.getProperty("browserstack.ios.version"));
            caps.setCapability("appium:app", "bs://dcb89b7bc839f1aa4984cba6da41881e363be80d");
        }

        // Set other BrowserStack capabilities
        caps.setCapability("bstack:options",  getCommonBsOptions());
        //caps.setCapability("project", "Sauce Labs Mobile App");
        //caps.setCapability("build", "Build 1.0");
        //caps.setCapability("name", "Sauce Labs Test");
        caps.setCapability("appium:newCommandTimeout", 300);

        // Start the BrowserStack Local binary if needed
        if (Boolean.parseBoolean(properties.getProperty("browserstack.local"))) {
            try {
                startBrowserStackLocal();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            caps.setCapability("browserstack.local", "true");
        }

        try {
            return new AppiumDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"),caps);
        }catch (SessionNotCreatedException e) {
            System.err.println("Session could not be created. Details: " + e.getMessage());
            throw e;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void startBrowserStackLocal() throws Exception {
        local = new Local();
        Map<String, String> options = new HashMap<>();
        options.put("key", BROWSERSTACK_ACCESS_KEY);
        local.start(options);
    }


    public void stopBrowserStackLocal() throws Exception {
        if (local != null) {
            local.stop();
        }
    }
}
