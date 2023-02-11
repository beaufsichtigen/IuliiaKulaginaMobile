package scenarios;

import static pageObjects.WebPages.GooglePage.googleURL;

import java.util.Locale;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.WebPages.GooglePage;
import setup.BaseTest;

public class WebHomeworkTest extends BaseTest {

    String searchText = "EPAM";

    //test will be started with other "web" tests
    @Parameters({"platformName"})
    @Test(groups = {"web"}, description = "Check search results for the 'EPAM' word")
    public void WebTest(String platformName) throws Exception {

        //load google search page
        getDriver().get(googleURL);
        waitUntilPageLoad();

        //create page object
        GooglePage page = new GooglePage(getDriver());

        System.out.println("Current platform: " + platformName);

        //Agree with cookies policy for Hungary.
        // If you have no this popup please commit.
        waitUntilPageLoad();
        page.acceptHungaryCookie(getWebDriverWait());

        //Fill search field and confirm
        page.fillSearchFieldAndConfirm(searchText, platformName);

        //Assert that result match request
        waitUntilPageLoad();
        Assert.assertTrue(page.isResultsContainsText(searchText), "Messages in result don't match search request");
    }
}
