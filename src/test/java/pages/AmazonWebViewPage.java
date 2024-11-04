package pages;

import com.aventstack.extentreports.Status;
import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ElementUtil;

import java.util.List;

import static context.WebDriverContext.getDriver;
import static report_manager.ExtentTestManager.getTest;

public class AmazonWebViewPage extends BasePage {

    @FindBy(xpath = "//a[@aria-label='Amazon.in']")
    private WebElement amazonWebViewLabel;
    @FindBy(xpath = "//input[@aria-label='Search Amazon.in']")
    private WebElement amazonSearchTextBox;
    @FindBy(xpath = "//input[@aria-label='Go']")
    private WebElement amazonSearchIcon;
//    @FindBy(xpath = "(//div[@class='puisg-row']/span/span/a/div/div/div/span[1]/div/img)[2]")
//    private WebElement searchResultSecondItem;
    @FindBy(xpath = "//div[@data-component-type='s-search-result']")
    List<WebElement> searchResults;
    @FindBy(xpath = "//div[@id='horizontalMediaCarousel']")
    private WebElement selectedItem;
    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    private WebElement addToCartButton;
    @FindBy(xpath = "//div[contains(@class,'added-to-cart-success')]/span[text()='Added to cart']")
    private WebElement addedToCartLabel;
    @FindBy(xpath = "//button[@class='sparkle-close-btn']")
    private WebElement closeIconLoginToAmazonAccount;

    public AmazonWebViewPage(WebDriver driver) {
        super(driver);
    }

    public void verifyAmazonWebViewPageIsDisplayed() {
        waitFor.until(ExpectedConditions.visibilityOf(amazonWebViewLabel));
        Assert.assertTrue("Amazon WebView page is not displayed", amazonWebViewLabel.isDisplayed());
        getTest().log(Status.PASS, "Amazon WebView page is displayed");
        System.out.println("Amazon WebView page is displayed");
    }

    public void searchProductInAmazonWebsite(String product) {
        amazonSearchTextBox.sendKeys(product);
        amazonSearchIcon.click();
        getTest().log(Status.PASS, product + " is searched");
        System.out.println(product + " is searched");
    }
    public void selectTheProductInSearchResults(int no) {
        try{
            Thread.sleep(1000);
            waitFor.until(ExpectedConditions.visibilityOf(searchResults.get(no-1)));
            searchResults.get(no-1).click();
            getTest().log(Status.PASS, "Selecting item from search results is clicked");
            System.out.println("Selecting "+no+" item from search results is clicked");
        } catch (InterruptedException e) {
            System.out.println("Exception occurred");
        }catch (ElementClickInterceptedException e){
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", searchResults.get(no-1));
        }
    }

    public void selectSecondProductInSearchResults() {
        try{
            Thread.sleep(2000);
            searchResults.get(1).click();
            getTest().log(Status.PASS, "Second item from search results is clicked");
            System.out.println("Second item from search results is clicked");
        } catch (InterruptedException e) {
            System.out.println("Exception occurred");
        }
    }

    public void verifyItemIsSelected() {
        waitFor.until(ExpectedConditions.visibilityOf(selectedItem));
        Assert.assertTrue("Selected item is not displayed", selectedItem.isDisplayed());
        getTest().log(Status.PASS, "Selected item is displayed");
        System.out.println("Selected item is displayed");
    }

    public void clickAddToCartButton() {
        ElementUtil.scrollInWebViewUntilElementIsVisible(addToCartButton);
        ElementUtil.scrollInWebView("up");
        waitFor.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
        getTest().log(Status.PASS, "Add to cart button on Amazon web view page is clicked");
        System.out.println("Add to cart button on Amazon web view page is clicked");
    }

    public void verifyProductIsAddedToCart() {
        waitFor.until(ExpectedConditions.visibilityOf(addedToCartLabel));
        Assert.assertTrue("Product is not added to cart", addedToCartLabel.isDisplayed());
        getTest().log(Status.PASS, "Product is added to cart successfully");
        System.out.println("Product is added to cart successfully");
    }
}
