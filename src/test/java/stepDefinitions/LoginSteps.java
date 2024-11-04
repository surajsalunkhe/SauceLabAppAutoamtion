package stepDefinitions;

import io.cucumber.java.en.When;
import pageFactory.PageFactoryInstances;
import pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage;

    public LoginSteps() {
        loginPage = PageFactoryInstances.getInstance(LoginPage.class);
    }

    @When("user login to the app with valid credentials")
    public void user_login_to_the_app_with_valid_credentials() {
        loginPage.loginToSauceLabsApp();
    }
}
