package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageFactory.PageFactoryInstances;
import pages.WebViewPage;

public class WebViewSteps {

    WebViewPage webViewPage;

    public WebViewSteps() {
        webViewPage = PageFactoryInstances.getInstance(WebViewPage.class);
    }

    @Then("user should be displayed with WebView page")
    public void user_is_navigated_to_homepage() {
        webViewPage.verifyWebViewPageIsDisplayed();
    }

    @Given("user enter url in the url section")
    public void user_enter_url_in_url_section() {
        webViewPage.enterURLInWebViewPage();
    }
}
