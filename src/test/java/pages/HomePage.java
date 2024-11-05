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

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"PRODUCTS\"]")
    private WebElement productsLabel;
    @AndroidFindBy(accessibility = "test-Modal Selector Button")
    @iOSXCUITFindBy(accessibility = "test-Modal Selector Button")
    private WebElement filterIcon;

    private String elementXpath;
    private String filterOptionXpath;
    private String selectProductXpath;

    public HomePage(WebDriver driver) {
        super(driver);
        if (properties.getProperty("platform").equalsIgnoreCase("android")) {
            // Android XPaths
            elementXpath = "//android.view.ViewGroup[@content-desc=\"test-<ELEMENT_NAME>\"]";
            filterOptionXpath = "//android.widget.TextView[@text=\"<OPTION_NAME>\"]";
            selectProductXpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"<PRODUCT_NAME>\"]";
        } else if (properties.getProperty("platform").equalsIgnoreCase("android")) {
            // iOS XPaths
            elementXpath = "//XCUIElementTypeOther[@name=\"test-<ELEMENT_NAME>\"]";
            filterOptionXpath = "//XCUIElementTypeStaticText[@label=\"<OPTION_NAME>\"]";
            selectProductXpath = "//XCUIElementTypeStaticText[@name=\"test-Item title\" and @label=\"<PRODUCT_NAME>\"]";
        }
    }

        public void verifyHomePageIsDisplayed() {
        waitFor.until(ExpectedConditions.visibilityOf(productsLabel));
        Assert.assertTrue("Home page is not displayed", productsLabel.isDisplayed());
        getTest().log(Status.PASS, "Home page is displayed");
        System.out.println("Home page is displayed");
    }

    public void clickElement(String elementName) {
        String afterElementReplace=elementXpath.replaceAll("<ELEMENT_NAME>", elementName);
        System.out.println(afterElementReplace);
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(afterElementReplace)));
        getDriver().findElement(AppiumBy.xpath(afterElementReplace)).click();
        getTest().log(Status.PASS, elementName + " button is clicked");
        System.out.println(elementName + " button is clicked");
    }

    /**
     * This method is used to click filter icon on homepage
     */
    public void clickFilterIcon() {
        waitFor.until(ExpectedConditions.visibilityOf(filterIcon));
        filterIcon.click();
        getTest().log(Status.PASS, "Filter icon is clicked");
        System.out.println("Filter icon is clicked");
    }

    /**
     * This method is used click a specific filter option
     * @param optionName    name of filter option to be selected
     */
    public void clickFilterOption(String optionName) {
        String afterFilterReplace=filterOptionXpath.replaceAll("<OPTION_NAME>", optionName);
        System.out.println(afterFilterReplace);
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(afterFilterReplace)));
        getDriver().findElement(AppiumBy.xpath(afterFilterReplace)).click();
        getTest().log(Status.PASS, optionName + " filter option is clicked");
        System.out.println(optionName + " filter option is clicked");
    }

    /**
     * This method is used to select a specific product
     * @param productName   name of product to be selected
     */
    public void selectProduct(String productName) {
        String afterProductReplace=selectProductXpath.replaceAll("<PRODUCT_NAME>", productName);
        System.out.println(afterProductReplace);
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(afterProductReplace)));
        getDriver().findElement(AppiumBy.xpath(afterProductReplace)).click();
        getTest().log(Status.PASS, productName + " product is selected");
        System.out.println(productName + " product is selected");
    }
}
