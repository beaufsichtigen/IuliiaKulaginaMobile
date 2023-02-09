package scenarios;

import static pageObjects.WebPages.GooglePage.googleURL;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.WebPages.GooglePage;
import setup.BaseTest;

public class webHomeworkTest extends BaseTest {

    String searchText = "EPAM";

    //test will be started with other "web" tests
    @Test(groups = {"web"}, description = "Check EPAM word search results")
    public void WebTest() {

        //load google search page
        getDriver().get(googleURL);

        //create page object
        GooglePage page = new GooglePage(getDriver());
        page.fillSearchField(searchText);
        getDriver().getKeyboard().pressKey(Keys.ENTER);

        //Assert that result match request
        waitUntilPageLoad();
        Assert.assertTrue(page.isResultsContainsText(searchText), "Messages in result don't match search request");
    }
}
