package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static report_manager.ExtentTestManager.getTest;
import static Hooks.Hooks.properties;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(accessibility = "test-Username")
    private WebElement usernameTextBox;

    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(accessibility = "test-Password")
    private WebElement passwordTextBox;

    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton;



    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void loginToSauceLabsApp() {
        try {
            waitFor.until(ExpectedConditions.visibilityOf(usernameTextBox));
            usernameTextBox.sendKeys(properties.getProperty("username"));
            passwordTextBox.sendKeys(properties.getProperty("password"));
            loginButton.click();
            getTest().log(Status.PASS, "Logged in successfully");
            System.out.println("Logged in successfully");
        } catch (Exception e) {
            getTest().log(Status.FAIL, "User was unable to login to the app");
            throw new RuntimeException(e);
        }
    }
}
