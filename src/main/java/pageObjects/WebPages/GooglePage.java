package pageObjects.WebPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends WebPageObject {

    @iOSXCUITFindBy(xpath = "//input")
    @FindBy(xpath = "//input")
    private WebElement searchField;

    @iOSXCUITFindBy(xpath = "//*[@id='rso']/*")
    @FindBy(xpath = "//*[@id='rso']/*")
    private List<WebElement> searchResults;

    public WebElement getEnterButton() {
        return enterButton;
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='go']")
    private WebElement enterButton;


    //Change "Olvasson" to your scroll down button text if it differs

    public static final String scrollXpath = "//button//*[contains(text(),'Olvasson')]";

    //Change "eluta" to your accept cookie button text if it differs
    public static final String acceptButtonXpath = "//button//*[contains(text(),'eluta')]";

    @iOSXCUITFindBy(xpath = scrollXpath)
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
        boolean resultCheck = false;
        for (WebElement result : searchResults) {
            String text =  result.getText();
            if (text.contains(search)) {
                resultCheck = true;
                System.out.println(text);
                break;
            }
        }
        return resultCheck;
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
