package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static context.WebDriverContext.getDriver;
import static report_manager.ExtentTestManager.getTest;

public class CartPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"YOUR CART\"]")
    private WebElement yourCartLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"$9.99\"]")
    private WebElement productPriceLabel;
    private String productDetailsInCartXpath = "//android.widget.TextView[@text=\"<PRODUCT_DETAIL>\"]";

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
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(productDetailsInCartXpath.replaceAll("<PRODUCT_DETAIL>", productDetail))));
        Assert.assertTrue(productDetail + " is not displayed in cart", getDriver().findElement(AppiumBy.xpath(productDetailsInCartXpath.replaceAll("<PRODUCT_DETAIL>", productDetail))).isDisplayed());
        getTest().log(Status.PASS, productDetail + " is displayed in cart");
        System.out.println(productDetail + " is displayed in cart");
    }

    public void verifyProductPriceInCart() {
        Assert.assertTrue("Product price is not displayed in cart", productPriceLabel.isDisplayed());
        getTest().log(Status.PASS, "Product price is displayed in cart");
        System.out.println("Product price is displayed in cart");
    }
}
