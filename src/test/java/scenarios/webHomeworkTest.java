package scenarios;

import java.net.MalformedURLException;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.WebPages.GooglePage;
import setup.BaseTest;

public class webHomeworkTest extends BaseTest {
    public webHomeworkTest() throws MalformedURLException {}

    //test will be started with other "web" tests
    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void WebTest() throws InterruptedException {

        //load google search page
        getDriver().get("http://www.google.com");

        String searchText = "EPAM";

        //create page object
        GooglePage page = new GooglePage(getDriver());
        page.fillSearchField(searchText);
        getDriver().getKeyboard().pressKey(Keys.ENTER);

        //Assert that result match request
        waitUntilPageLoad();
        Assert.assertTrue(page.isResultsContainsText(searchText));
    }
}
