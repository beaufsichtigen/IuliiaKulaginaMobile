package scenarios;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setup.BaseTest;

public class nativeHomeworkTest extends BaseTest {

    @Test(groups = {"native"}, description = "Budget Activity page check")
    public void nativeTest() throws InterruptedException, NoSuchFieldException {

        //Go to register page
        getLogInPage().clickRegisterButton();

        //Wait when the page opens
        getWebDriverWait().until(ExpectedConditions.visibilityOf(getRegisterPage().getRegisterBtn()));

        //get credentials from environment variables
        String email = System.getenv("email");
        String password = System.getenv("password");
        String username = System.getenv("username");

        //Input credentials
        getRegisterPage().fillRegisterForm(email, username, password);

        //Try to set checkbox true
        //AndroidElement element = (AndroidElement) getRegisterPage().getReadCheckbox();
        //System.out.println(element.getAttribute("checked"));
        //getDriver().executeScript("arguments[0].setAttribute('checked', 'true');", element);
        // System.out.println(element.getAttribute("checked"));

        //click register
        getRegisterPage().getRegisterBtn().click();

        getWebDriverWait().until(ExpectedConditions.visibilityOf(getLogInPage().getSignInBtn()));

        //Try to sign
        getLogInPage().signIn(email, password);

        //Assert that there is text "BudgetActivity" and Add expense button
        getWebDriverWait().until(ExpectedConditions.visibilityOf(getBudgetActivityPage().getBudgetActivity()));;

        //Check that there are any element with "BudgetActivity" text
        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(getBudgetActivityPage().getBudgetActivity());
        //Check that there are button Add Expense
        soft.assertTrue(getBudgetActivityPage().getAddExpenseBtn().isDisplayed());

        soft.assertAll();
    }
}
