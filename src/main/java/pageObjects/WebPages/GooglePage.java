package pageObjects.WebPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    @FindBy(xpath = "//button//*[contains(text(),'Olvasson')]")
    private WebElement scrollButton;

    //Change "eluta" to your accept cookie button text if it differs
    @FindBy(xpath = "//button//*[contains(text(),'eluta')]")
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

    public WebElement getScrollButton() {
        return scrollButton;
    }

    public WebElement getAcceptButton() {
        return acceptButton;
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }

    public void acceptHungaryCookie(AppiumFluentWait wait) {
        if (getScrollButton().isDisplayed()) {
            getScrollButton().click();
            wait.until(ExpectedConditions.visibilityOf(getScrollButton()));
            getScrollButton().click();
            wait.until(ExpectedConditions.visibilityOf(getAcceptButton()));
            getAcceptButton().click();
        }
    }

    public void fillSearchFieldAndConfirm(String searchText, String platformName) {

        switch (platformName.toUpperCase(Locale.ROOT)) {
            case "ANDROID":
                //Fill search field
                fillSearchField(searchText);
                driver.getKeyboard().pressKey(Keys.ENTER); //don't work for the IOs
                break;
            case "IOS":
                getSearchField().click();
                getSearchField().sendKeys(searchText + Keys.ENTER); //tried + "\n" as well
                break;
            default: System.out.println("Can't clickEnter " + platformName);
        }
    }
}
