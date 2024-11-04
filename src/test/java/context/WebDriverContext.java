package context;

import org.openqa.selenium.WebDriver;

public class WebDriverContext {

    // The driver instance.
    private static final ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverInstance.get() == null) {
            throw new IllegalStateException("WebDriver has not been set");
        }
        else
            return driverInstance.get();
    }
    public static void setDriver(WebDriver driver) {
        driverInstance.set(driver);
    }
}
