package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Arrays;

import static Hooks.Hooks.properties;

public class BasePage {
    protected WebDriver driver;
    protected FluentWait<WebDriver> waitFor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        waitFor = new FluentWait<>(driver).ignoring(NoSuchElementException.class, WebDriverException.class)
                .withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5));
    }
    public void tapOnElementUsingPointerInput(WebElement element) {
        // Calculate the center coordinates of the element
        int x = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int y = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        tapUsingPointerInput(x, y);
    }

    public void tapUsingPointerInput(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence tapSequence = new Sequence(finger, 0);
        tapSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tapSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tapSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        if(properties.getProperty("platform").equalsIgnoreCase("Android")){
            ((AndroidDriver)driver).perform(Arrays.asList(tapSequence));
        }else{
            ((IOSDriver)driver).perform(Arrays.asList(tapSequence));
        }
    }
}
