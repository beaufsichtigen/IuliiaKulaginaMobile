package pageObjects.WebPages;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends WebPageObject {
    public WebElement getSearchField() {
        return searchField;
    }

    @FindBy(xpath = "//input")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='rso']/*")
    private List<WebElement> searchResults;

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
}
