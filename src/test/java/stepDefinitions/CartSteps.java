package stepDefinitions;

import io.cucumber.java.en.Then;
import pageFactory.PageFactoryInstances;
import pages.CartPage;

public class CartSteps {

    CartPage cartPage;

    public CartSteps() {
        cartPage = PageFactoryInstances.getInstance(CartPage.class);
    }

    @Then("user should be displayed with Cart page")
    public void user_should_be_displayed_with_cart_page() {
        cartPage.verifyCartPageIsDisplayed();
    }

    @Then("user should be displayed with {string} product in the cart")
    public void user_should_be_displayed_with_product_in_the_cart(String productName) {
        cartPage.verifyProductDetailsInCart(productName);
    }

    @Then("user should be displayed with price $9.99 in the cart")
    public void user_should_be_displayed_with_price_$9_99_in_the_cart() {
        cartPage.verifyProductPriceInCart();
    }
}
