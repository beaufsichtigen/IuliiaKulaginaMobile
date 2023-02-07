package scenarios;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.WebPages.GooglePage;
import setup.BaseTest;

public class webHomeworkTest extends BaseTest {

    //test will be started with other "web" tests
    @Test(groups = {"web"}, description = "Check search results for the 'EPAM' word")
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
        //page.getEnterButton().click();
        getDriver().getKeyboard().pressKey(Keys.ENTER); //don't work for the IOs
        //new TouchAction(getDriver()).press(PointOption.point(1000, 2050)).release().perform();

        //Assert that result match request
        getWebDriverWait().until(ExpectedConditions.visibilityOf(page.getSearchResults().get(0)));
        ;
        Assert.assertTrue(page.isResultsContainsText(searchText));
    }
}
