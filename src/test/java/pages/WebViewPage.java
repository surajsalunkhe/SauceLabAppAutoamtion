package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static report_manager.ExtentTestManager.getTest;
import static Hooks.Hooks.properties;

public class WebViewPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"WEBVIEW SELECTION\"]")
    private WebElement webViewLabel;
    @AndroidFindBy(accessibility = "test-enter a https url here...")
    private WebElement enterURLTextBox;

    public WebViewPage(WebDriver driver) {
        super(driver);
    }

    public void verifyWebViewPageIsDisplayed() {
        waitFor.until(ExpectedConditions.visibilityOf(webViewLabel));
        Assert.assertTrue("WebView page is not displayed", webViewLabel.isDisplayed());
        getTest().log(Status.PASS, "WebView page is displayed");
        System.out.println("WebView page is displayed");
    }

    public void enterURLInWebViewPage() {
        enterURLTextBox.sendKeys(properties.getProperty("webViewURL"));
        getTest().log(Status.PASS, properties.getProperty("webViewURL") + " URL is entered");
        System.out.println(properties.getProperty("webViewURL") + " URL is entered");
    }
}
