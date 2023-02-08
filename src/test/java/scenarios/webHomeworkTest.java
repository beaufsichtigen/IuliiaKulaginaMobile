package scenarios;

import java.util.Locale;
import org.openqa.selenium.Keys;
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

        //Click enter
        switch (platformName.toUpperCase(Locale.ROOT)) {
            case "ANDROID":
                //Fill search field
                page.fillSearchField(searchText);
                getDriver().getKeyboard().pressKey(Keys.ENTER); //don't work for the IOs
                break;
            case "IOS":
                page.getSearchField().click();
                page.getSearchField().sendKeys(searchText + Keys.ENTER);
                break;
            default:
                throw new Exception("Can't click Enter on " + platformName);
        }

        //Assert that result match request
        waitUntilPageLoad();
        Assert.assertTrue(page.isResultsContainsText(searchText));
    }
}
