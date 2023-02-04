package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
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

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    IPageObject po;

    LogInPage logInPage;
    RegisterPage registerPage;

    AppiumFluentWait webDriverWait;
    BudgetActivityPage budgetActivityPage;

    @Override
    public AppiumDriver getDriver() { return appiumDriver; }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName","appType","deviceName","browserName","app"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName, @Optional("") String browserName, @Optional("") String app) throws Exception {
        System.out.println("Before: app type - "+appType);
        setAppiumDriver(platformName, deviceName, browserName, app);
        setPageObject(appType, appiumDriver);

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("deviceName",deviceName);

        if(app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck","true");

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }

    protected void waitUntilPageLoad(){
        new WebDriverWait(getDriver(), 10).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    //Getters for pages
    public LogInPage getLogInPage () {
        if (logInPage == null)
        return new LogInPage(appiumDriver);
        else return logInPage;
    }

    public RegisterPage getRegisterPage () {
        if (registerPage == null)
        return new RegisterPage(appiumDriver);
        else return registerPage;
    }

    public BudgetActivityPage getBudgetActivityPage () {
        if (webDriverWait == null)
        return new BudgetActivityPage(appiumDriver);
        else return budgetActivityPage;
    }

    public AppiumFluentWait getWebDriverWait () {
        if (webDriverWait == null)
            return new AppiumFluentWait(appiumDriver);
        else return webDriverWait;
    }
}
