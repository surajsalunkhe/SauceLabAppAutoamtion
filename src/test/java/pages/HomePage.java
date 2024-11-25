package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Hooks.Hooks.properties;
import static context.WebDriverContext.getDriver;
import static report_manager.ExtentTestManager.getTest;

public class HomePage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='PRODUCTS']")
    private WebElement productsLabel;

    @AndroidFindBy(accessibility = "test-Modal Selector Button")
    @iOSXCUITFindBy(accessibility = "test-Modal Selector Button")
    private WebElement filterIcon;

    private static final String ANDROID_ELEMENT_XPATH = "//android.view.ViewGroup[@content-desc='test-%s']";
    private static final String IOS_ELEMENT_XPATH = "//XCUIElementTypeOther[@name='test-%s']";

    private static final String ANDROID_FILTER_OPTION_XPATH = "//android.widget.TextView[@text='%s']";
    private static final String IOS_FILTER_OPTION_XPATH = "//XCUIElementTypeStaticText[@label='%s']";

    private static final String ANDROID_SELECT_PRODUCT_XPATH = "//android.widget.TextView[@content-desc='test-Item title' and @text='%s']";
    private static final String IOS_SELECT_PRODUCT_XPATH = "//XCUIElementTypeStaticText[@name='test-Item title' and @label='%s']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyHomePageIsDisplayed() {
        waitFor.until(ExpectedConditions.visibilityOf(productsLabel));
        Assert.assertTrue("Home page is not displayed", productsLabel.isDisplayed());
        getTest().log(Status.PASS, "Home page is displayed");
        System.out.println("Home page is displayed");
    }

    public void clickElement(String elementName) {
        String xpath = getPlatformSpecificXpath(ANDROID_ELEMENT_XPATH, IOS_ELEMENT_XPATH, elementName);
        //System.out.println(xpath);
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath)));
        getDriver().findElement(AppiumBy.xpath(xpath)).click();
        getTest().log(Status.PASS, elementName + " button is clicked");
        System.out.println(elementName + " button is clicked");
    }

    /**
     * This method is used to click filter icon on the homepage
     */
    public void clickFilterIcon() {
        waitFor.until(ExpectedConditions.visibilityOf(filterIcon));
        filterIcon.click();
        getTest().log(Status.PASS, "Filter icon is clicked");
        System.out.println("Filter icon is clicked");
    }

    /**
     * This method is used to click a specific filter option
     * @param optionName name of filter option to be selected
     */
    public void clickFilterOption(String optionName) {
        String xpath = getPlatformSpecificXpath(ANDROID_FILTER_OPTION_XPATH, IOS_FILTER_OPTION_XPATH, optionName);
        System.out.println(xpath);
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath)));
        getDriver().findElement(AppiumBy.xpath(xpath)).click();
        getTest().log(Status.PASS, optionName + " filter option is clicked");
        System.out.println(optionName + " filter option is clicked");
    }

    /**
     * This method is used to select a specific product
     * @param productName name of the product to be selected
     */
    public void selectProduct(String productName) {
        String xpath = getPlatformSpecificXpath(ANDROID_SELECT_PRODUCT_XPATH, IOS_SELECT_PRODUCT_XPATH, productName);
        System.out.println(xpath);
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath)));
        getDriver().findElement(AppiumBy.xpath(xpath)).click();
        getTest().log(Status.PASS, productName + " product is selected");
        System.out.println(productName + " product is selected");
    }
}

