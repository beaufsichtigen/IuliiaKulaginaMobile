package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import java.net.URLEncoder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObjects.NativePages.BudgetActivityPage;
import pageObjects.NativePages.LogInPage;
import pageObjects.NativePages.RegisterPage;
import pageObjects.PageObject;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import pageObjects.WebPages.GooglePage;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    IPageObject po;

    LogInPage logInPage;
    RegisterPage registerPage;

    AppiumFluentWait webDriverWait;
    BudgetActivityPage budgetActivityPage;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName", "appType", "deviceName", "udid", "browserName", "app", "appPackage", "appActivity",
        "bundleId"})
    @BeforeClass(alwaysRun = true) //changed for running two tests in one suite
    public void setUp(String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
    }

    @AfterClass(alwaysRun = true) //changed for running two tests in one suite
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName,
                                 String app, String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        // Capabilities for test of Android native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        // Capabilities for test of iOS native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId", bundleId);
        //        if(platformName.equals("iOS")) capabilities.setCapability("automationName","XCUITest");

        try {

            String url = "https://" + System.getenv("USERNAMEMOBIT") + ":" + URLEncoder.encode(System.getenv("TOKEN"))
                + "@app.mobitru.com/wd/hub";
            System.out.println("url:" + url + capabilities);
            appiumDriver = new AppiumDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }

    protected void waitUntilPageLoad() {
        new WebDriverWait(getDriver(), 10).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    //Getters for pages
    public LogInPage getLogInPage() {
        if (logInPage == null) {
            return new LogInPage(appiumDriver);
        } else {
            return logInPage;
        }
    }

    public RegisterPage getRegisterPage() {
        if (registerPage == null) {
            return new RegisterPage(appiumDriver);
        } else {
            return registerPage;
        }
    }

    public BudgetActivityPage getBudgetActivityPage() {
        if (webDriverWait == null) {
            return new BudgetActivityPage(appiumDriver);
        } else {
            return budgetActivityPage;
        }
    }

    public AppiumFluentWait getWebDriverWait() {
        if (webDriverWait == null) {
            return new AppiumFluentWait(appiumDriver);
        } else {
            return webDriverWait;
        }
    }

    public void acceptHungaryCookie(GooglePage page) {
        waitUntilPageLoad();
        if (page.getButtons().isDisplayed()) {
            page.getButtons().click();
            waitUntilPageLoad();
            page.getButtons().click();
            waitUntilPageLoad();
            page.getAcceptButton().click();
        }
    }
}
