package pageObjects.NativePages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BudgetActivityPage extends BaseNativePage {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='Budget']")
    @FindBy(xpath = "//*[contains(@text,'BudgetActivity')]")
    private WebElement budgetActivityLabel;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Add']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/add_new_expense")
    private WebElement addExpenseBtn;

    public BudgetActivityPage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement getAddExpenseBtn() {
        return addExpenseBtn;
    }

    public WebElement getBudgetActivityLabel() {
        return budgetActivityLabel;
    }
}
