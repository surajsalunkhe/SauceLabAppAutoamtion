package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.ElementUtil;
import utils.SwitchContextUtil;

import java.net.MalformedURLException;

import static context.WebDriverContext.getDriver;
import static Hooks.Hooks.properties;

public class BaseSteps {

    @Given("user installs the Sauce Labs app")
    public void user_installs_the_sauce_labs_app() throws MalformedURLException {
        DriverFactory.createMobileInstance(properties.getProperty("platform"));
    }

    @When("user scroll {string} with scroll ratio {double}")
    public void user_scroll_with_scroll_ratio(String direction, double scrollRatio) {
        ElementUtil.scroll(null, direction, scrollRatio);
    }

    @When("user change context to {string}")
    public void user_change_context(String contextType) {
        SwitchContextUtil.changeDriverContext(getDriver(), contextType, properties.getProperty("platform"));
    }

    @When("user scroll {string} in web view page")
    public void user_scroll_down_in_web_view_page(String direction) {
        ElementUtil.scrollInWebView(direction);
    }
}
