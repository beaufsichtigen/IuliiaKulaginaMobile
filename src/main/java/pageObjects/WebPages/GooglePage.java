package pageObjects.WebPages;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends WebPageObject {

    public static final String googleURL = "https://www.google.com";

    @FindBy(xpath = "//input")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='rso']/*")
    private List<WebElement> searchResultsList;

    public GooglePage(AppiumDriver driver) {
        super(driver);
    }

    public void fillSearchField(String search) {
        searchField.click();
        searchField.sendKeys(search);
    }

    public boolean isResultsContainsText(String search) {
        for (WebElement result : searchResultsList) {
            String text = result.getText();
            if (text.contains(search)) {
                System.out.println(text);
                return true;
            }
        }
        return false;
    }
}
