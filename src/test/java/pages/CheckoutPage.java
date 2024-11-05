package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

import static report_manager.ExtentTestManager.getTest;

public class CheckoutPage extends BasePage {

    @AndroidFindBy(accessibility = "test-CHECKOUT")
    @iOSXCUITBy(accessibility = "test-CHECKOUT")
    private WebElement checkoutLabel;

    @AndroidFindBy(accessibility = "test-First Name")
    @iOSXCUITBy(accessibility = "test-First Name")
    private WebElement firstNameTextBox;

    @AndroidFindBy(accessibility = "test-Last Name")
    @iOSXCUITBy(accessibility = "test-Last Name")
    private WebElement lastNameTextBox;

    @AndroidFindBy(accessibility = "test-Zip/Postal Code")
    @iOSXCUITBy(accessibility = "test-Zip/Postal Code")
    private WebElement zipCodeTextBox;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]")
    @iOSXCUITBy(xpath = "//XCUIElementTypeStaticText[contains(@label, \"THANK YOU FOR YOUR ORDER\")]")
    private WebElement orderPlacedMessageLabel;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void verifyCheckoutPageIsDisplayed() {
        waitFor.until(ExpectedConditions.visibilityOf(checkoutLabel));
        Assert.assertTrue("Checkout page is not displayed", checkoutLabel.isDisplayed());
        getTest().log(Status.PASS, "Checkout page is displayed");
        System.out.println("Checkout page is displayed");
    }

    /**
     * This method is used to enter required details in checkout page
     * @param testData  data to be entered
     */
    public void enterRequiredDetailAtCheckoutPage(DataTable testData) {
        Map<String, String> data = testData.asMap(String.class, String.class);
        firstNameTextBox.sendKeys(data.get("FirstName"));
        lastNameTextBox.sendKeys(data.get("LastName"));
        zipCodeTextBox.sendKeys(data.get("ZipCode"));
        getTest().log(Status.PASS, "Details entered on checkout page");
        System.out.println("Details entered on checkout page");
    }

    /**
     * This method is used to verify if order is placed successfully
     */
    public void verifyOrderPlacedSuccessfully() {
        waitFor.until(ExpectedConditions.visibilityOf(orderPlacedMessageLabel));
        Assert.assertTrue("Order is not placed", orderPlacedMessageLabel.isDisplayed());
        getTest().log(Status.PASS, "Order is placed successfully");
        System.out.println("Order is placed successfully");
    }
}
