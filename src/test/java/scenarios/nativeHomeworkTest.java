package scenarios;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setup.BaseTest;

public class nativeHomeworkTest extends BaseTest {
    public nativeHomeworkTest() throws MalformedURLException {}

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button")
    public void nativeTest() {

        //Go to register page
        getLogInPage().clickRegisterButton();

        //Wait when the page opens
        getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(getRegisterPage().getIdAllRegisterForm())));

        //get credentials from environment variables
        String email = System.getenv("email");
        String password = System.getenv("password");
        String username = System.getenv("username");

        //Input credentials

        getRegisterPage().fillRegisterForm(email, username, password);

        //click register
        getRegisterPage().getRegisterBtn().click();

        //Try to sign in
        getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(getLogInPage().getIdAllLoginForm())));
        getLogInPage().signIn(email, password);

        //Assert that there is text "BudgetActivity" and Add expense button
        getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(getBudgetActivityPage().getIdAllBudgetActivity())));
        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(getBudgetActivityPage().getBudgetActivity()); //Check that there are any element with "BudgetActivity" text
        soft.assertEquals(getBudgetActivityPage().getAddExpenseBtn().getText(), "ADD EXPENSE"); //Check that there are button Add Expense

        soft.assertAll();
        //getDriver().getExecuteMethod().execute("am start -n platkovsky.alexey.epamtestapp/platkovsky.alexey.epamtestapp.activities.LoginActivity", null);
    }
}
