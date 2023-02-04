package pageObjects.WebPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject  {

    String baseUrl = "https://www.google.com/";

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
        appiumDriver.get(baseUrl);
    }
}
