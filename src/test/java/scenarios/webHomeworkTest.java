package scenarios;

import java.util.Locale;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.WebPages.GooglePage;
import setup.BaseTest;

public class webHomeworkTest extends BaseTest {

    //test will be started with other "web" tests
    @Parameters({"platformName"})
    @Test(groups = {"web"}, description = "Check search results for the 'EPAM' word")
    public void WebTest(String platformName) throws Exception {

        //load google search page
        getDriver().get("https://www.google.com");
        waitUntilPageLoad();

        String searchText = "EPAM";

        //create page object
        GooglePage page = new GooglePage(getDriver());

        System.out.println("Current platform: " + platformName);

        //Agree with cookies policy for Hungary.
        // If you have no this popup please commit.
        acceptHungaryCookie(page);

        //Fill search field
        page.fillSearchField(searchText);

        //Click enter
        switch (platformName.toUpperCase(Locale.ROOT)) {
            case "ANDROID":
                getDriver().getKeyboard().pressKey(Keys.ENTER); //don't work for the IOs
                break;
            case "IOS":
                page.getSearchField().sendKeys(searchText + Keys.ENTER);
                break;
            default:
                throw new Exception("Can't create a page object for " + platformName);
        }

        //Assert that result match request
        getWebDriverWait().until(ExpectedConditions.visibilityOf(page.getSearchResults().get(0)));

        Assert.assertTrue(page.isResultsContainsText(searchText));
    }
}
