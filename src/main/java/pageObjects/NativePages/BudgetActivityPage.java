package pageObjects.NativePages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BudgetActivityPage extends BaseNativePage {

    private String idAllBudgetActivity = "platkovsky.alexey.epamtestapp:id/main_content";

    @FindBy(xpath = "//*[contains(@text,'BudgetActivity')]")
    private WebElement budgetActivity;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/add_new_expense")
    private WebElement addExpenseBtn;

    public BudgetActivityPage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement getAddExpenseBtn() {
        return addExpenseBtn;
    }

    public WebElement getBudgetActivity() {
        return budgetActivity;
    }

    public String getIdAllBudgetActivity() {
        return idAllBudgetActivity;
    }
}
