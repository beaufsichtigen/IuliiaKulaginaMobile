package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.WebPages.GooglePage;
import setup.BaseTest;

import static pageObjects.WebPages.GooglePage.acceptButtonXpath;
import static pageObjects.WebPages.GooglePage.scrollXpath;

public class webHomeworkTest extends BaseTest {

    //test will be started with other "web" tests
    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void WebTest() throws InterruptedException {

        //load google search page
        getDriver().get("https://www.google.com");
        waitUntilPageLoad();

        String searchText = "EPAM";

        //create page object
        GooglePage page = new GooglePage(getDriver());

        //Agree with cookies policy for Hungary.
        // If you have no this popup please commit.
        acceptHungaryCookie(page);


        page.fillSearchField(searchText);
        getDriver().getKeyboard().pressKey(Keys.ENTER);

        //Assert that result match request
        waitUntilPageLoad();
        Assert.assertTrue(page.isResultsContainsText(searchText));
    }


}
