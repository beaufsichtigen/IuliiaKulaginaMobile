package scenarios;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import setup.BaseTest;

public class webMobileTests extends BaseTest {

    String ianaURL = "http://iana.org";

    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest() {
        getDriver().get(ianaURL); // open IANA homepage

        // Make sure that page has been loaded completely
        waitUntilPageLoad();

        // Check IANA homepage title
        assert ((WebDriver) getDriver()).getTitle().equals("Internet Assigned Numbers Authority") :
            "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }
}
