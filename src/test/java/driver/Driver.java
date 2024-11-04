package driver;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface Driver {
    /** This method is used to create driver instance
     **/
    WebDriver createInstance() throws MalformedURLException;
}
