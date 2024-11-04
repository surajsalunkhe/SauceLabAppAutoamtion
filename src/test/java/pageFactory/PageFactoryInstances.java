package pageFactory;

import context.WebDriverContext;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.lang.reflect.InvocationTargetException;

public class PageFactoryInstances {

    /**Gets the single instance of PageFactoryInstances.*/
    public static <T extends BasePage> T getInstance(Class<T> type) {
        try {
            return type.getConstructor(WebDriver.class).newInstance(WebDriverContext.getDriver());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException e) {
            return null;
        }
    }
}
