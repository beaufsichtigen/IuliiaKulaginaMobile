package pageObjects.WebPages;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends WebPageObject {

    public static final String googleURL = "https://www.google.com";

    public WebElement getSearchField() {
        return searchField;
    }

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='rso']/*")
    private List<WebElement> searchResults;

    //Change "Olvasson" to your scroll down button text if it differs
    public static final String scrollXpath = "//button//*[contains(text(),'Olvasson')]";

    //Change "eluta" to your accept cookie button text if it differs
    public static final String acceptButtonXpath = "//button//*[contains(text(),'eluta')]";

    @FindBy(xpath = scrollXpath)
    private WebElement scrollButton;

    @FindBy(xpath = acceptButtonXpath)
    private WebElement acceptButton;

    public GooglePage(AppiumDriver driver) {
        super(driver);
    }

    public void fillSearchField(String search) {
        searchField.click();
        searchField.sendKeys(search);
    }

    public boolean isResultsContainsText(String search) {
        for (WebElement result : searchResults) {
            String text = result.getText();
            if (text.contains(search)) {
                System.out.println(text);
                return true;
            }
        } return false;
    }

    public WebElement getButtons() {
        return scrollButton;
    }

    public WebElement getAcceptButton() {
        return acceptButton;
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }
}
