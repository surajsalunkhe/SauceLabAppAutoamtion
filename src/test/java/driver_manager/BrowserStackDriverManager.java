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
        bstackOptions.put("userName", BROWSERSTACK_USERNAME);
        bstackOptions.put("accessKey", BROWSERSTACK_ACCESS_KEY);
        bstackOptions.put("appiumVersion", "2.0.1");
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
            caps.setCapability("platformName", "android");
            caps.setCapability("appium:platformVersion", "12.0");
            caps.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
            caps.setCapability("appium:app", "bs://a919ec50939a98d93baef4c0d6c6d4c958b642b2");
        } else if (properties.getProperty("platform").equalsIgnoreCase("ios")) {
            caps.setCapability("platformName", "ios");
            caps.setCapability("appium:platformVersion", "14.0");
            caps.setCapability("appium:deviceName", "iPhone 12 Pro Max");
            caps.setCapability("appium:app", "bs://408f9f93049e0a468949c00af2fc1ce39b3cbe31");
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
