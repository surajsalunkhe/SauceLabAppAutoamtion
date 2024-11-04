package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.PageFactoryInstances;
import pages.HomePage;

public class HomePageSteps {
    HomePage homePage;

    public HomePageSteps() {
        homePage = PageFactoryInstances.getInstance(HomePage.class);
    }

    @Then("user is navigated to homepage")
    public void user_is_navigated_to_homepage() {
        homePage.verifyHomePageIsDisplayed();
    }

    @Given("user clicks on {string} in the menu")
    public void user_clicks_on_in_the_menu(String menuName) {
        homePage.clickElement(menuName);
    }

    @When("user clicks on {string} option to apply filter")
    public void user_clicks_on_option_to_apply_filter(String filterOption) {
        homePage.clickFilterOption(filterOption);
    }

    @Given("user clicks on {string} product")
    public void user_clicks_on_product(String productName) {
        homePage.selectProduct(productName);
    }

    @When("user clicks on {string} button")
    public void user_clicks_on_button(String buttonName) {
        homePage.clickElement(buttonName);
    }

    @Given("user clicks on {string} icon")
    public void user_clicks_on_icon(String elementName) {
        homePage.clickElement(elementName);
    }

    @Given("user clicks on Filter icon")
    public void user_clicks_on_filter_icon() {
        homePage.clickFilterIcon();
    }
}
