package pageObjects.NativePages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BaseNativePage {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Sign In']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    private WebElement signInBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Register new account']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    private WebElement registerBtn;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@label='']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    private WebElement emailField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@label='']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    private WebElement passwordField;

    public LogInPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickRegisterButton() {
        registerBtn.click();
    }

    public void signIn(String emailOrUsername, String password) {
        emailField.sendKeys(emailOrUsername);
        passwordField.sendKeys(password);
        signInBtn.click();
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }

    public WebElement getRegisterBtn() {
        return registerBtn;
    }
}
