package driver;

import driver_manager.AndroidDriverManager;
import driver_manager.BrowserStackDriverManager;
import driver_manager.IOSDriverManager;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static Hooks.Hooks.properties;
import static context.WebDriverContext.setDriver;

public class DriverFactory {

    /* This method is used to set created driver based on platform type*/
    public static void createMobileInstance(String platform) throws MalformedURLException {
        WebDriver driver = null;
        PlatformList platformType = PlatformList.valueOf(platform.toUpperCase());

        if (Boolean.parseBoolean(properties.getProperty("use.browserstack"))) {
            driver = new BrowserStackDriverManager().createInstance();
        }else{

            switch (platformType) {
                case ANDROID:
                    driver = new AndroidDriverManager().createInstance();
                    setDriver(driver);
                    break;
                case IOS:
                    driver = new IOSDriverManager().createInstance();
                    setDriver(driver);
                    break;
            }
        }
    }

    public enum PlatformList {
        ANDROID, IOS;
    }
}
