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

public class CartPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='YOUR CART']")
    @iOSXCUITFindBy(accessibility = "test-ADD TO CART")
    private WebElement yourCartLabel;

    /*@AndroidFindBy(xpath = "//android.widget.TextView[@text='$9.99']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@text='$9.99']")
    private WebElement productPriceLabel;*/

    private static final String ANDROID_PRODUCT_PRICE_XPATH = "//android.widget.TextView[@text='%s']";
    private static final String IOS_PRODUCT_PRICE_XPATH = "//XCUIElementTypeStaticText[@text='%s']";

    private static final String ANDROID_PRODUCT_DETAILS_XPATH = "//android.widget.TextView[@text='%s']";
    private static final String IOS_PRODUCT_DETAILS_XPATH = "//XCUIElementTypeStaticText[@label='%s']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void verifyCartPageIsDisplayed() {
        waitFor.until(ExpectedConditions.visibilityOf(yourCartLabel));
        Assert.assertTrue("Cart page is not displayed", yourCartLabel.isDisplayed());
        getTest().log(Status.PASS, "Cart page is displayed");
        System.out.println("Cart page is displayed");
    }

    public void verifyProductDetailsInCart(String productDetail) {
        String xpath = getPlatformSpecificXpath(ANDROID_PRODUCT_DETAILS_XPATH, IOS_PRODUCT_DETAILS_XPATH, productDetail);
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath)));
        Assert.assertTrue(productDetail + " is not displayed in cart", getDriver().findElement(AppiumBy.xpath(xpath)).isDisplayed());
        getTest().log(Status.PASS, productDetail + " is displayed in cart");
        System.out.println(productDetail + " is displayed in cart");
    }

    public void verifyProductPriceInCart(String expectedPrice) {
        String xpath = getPlatformSpecificXpath(ANDROID_PRODUCT_PRICE_XPATH, IOS_PRODUCT_PRICE_XPATH, expectedPrice);
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath)));
        WebElement productPriceElement = getDriver().findElement(AppiumBy.xpath(xpath));
        Assert.assertTrue("Product price is not displayed in cart", productPriceElement.isDisplayed());
        getTest().log(Status.PASS, "Product price is displayed in cart");
        System.out.println("Product price is displayed in cart");
    }
}