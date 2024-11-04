package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.PageFactoryInstances;
import pages.AmazonWebViewPage;

public class AmazonWebViewSteps {

    AmazonWebViewPage amazonWebViewPage;

    public AmazonWebViewSteps() {
        amazonWebViewPage = PageFactoryInstances.getInstance(AmazonWebViewPage.class);
    }

    @Then("user should be displayed with Amazon website homepage")
    public void user_should_be_displayed_with_amazon_website_homepage() {
        amazonWebViewPage.verifyAmazonWebViewPageIsDisplayed();
    }

    @Given("user search for {string} at Amazon website")
    public void user_search_for_at_amazon_website(String product) {
        amazonWebViewPage.searchProductInAmazonWebsite(product);
    }

    @When("user select the second item from search results")
    public void user_select_second_item_from_search_results() {
        amazonWebViewPage.selectSecondProductInSearchResults();
    }

    @When("user select the {int} item from search results")
    public void user_select_the_item_from_search_results(int no) {
        amazonWebViewPage.selectTheProductInSearchResults(no);
    }

    @Then("user should be displayed with item selected")
    public void user_should_be_displayed_with_item_selected() {
        amazonWebViewPage.verifyItemIsSelected();
    }

    @When("user clicks on add to cart button on amazon web view page")
    public void user_clicks_on_add_to_cart_button_on_amazon_web_view_page() {
        amazonWebViewPage.clickAddToCartButton();
    }

    @Then("product should be added to cart successfully")
    public void product_should_be_added_to_cart_successfully() {
        amazonWebViewPage.verifyProductIsAddedToCart();
    }
}
