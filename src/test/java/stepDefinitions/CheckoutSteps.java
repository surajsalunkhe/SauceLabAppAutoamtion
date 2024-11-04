package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.PageFactoryInstances;
import pages.CheckoutPage;

public class CheckoutSteps {

    CheckoutPage checkoutPage;

    public CheckoutSteps() {
        checkoutPage = PageFactoryInstances.getInstance(CheckoutPage.class);
    }

    @Then("user should be displayed with Checkout page")
    public void user_should_be_displayed_with_checkout_page() {
        checkoutPage.verifyCheckoutPageIsDisplayed();
    }

    @When("user enter required details on the Checkout page")
    public void user_enter_required_details_on_the_checkout_page(DataTable testData) {
        checkoutPage.enterRequiredDetailAtCheckoutPage(testData);
    }

    @Then("user should be able to place order successfully")
    public void user_should_be_able_to_place_order_successfully() {
        checkoutPage.verifyOrderPlacedSuccessfully();
    }
}
