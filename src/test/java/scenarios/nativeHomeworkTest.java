package scenarios;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setup.BaseTest;

public class nativeHomeworkTest extends BaseTest {

    @Test(groups = {"native"}, description = "Budget Activity page check")
    public void nativeTest() {

        //Go to register page
        getLogInPage().clickRegisterButton();

        //Wait when the page opens
        waitContentLoadByWebelement(getRegisterPage().getRegisterBtn());

        //get credentials from environment variables
        String email = System.getenv("email");
        String password = System.getenv("password");
        String username = System.getenv("username");

        //Input credentials
        getRegisterPage().fillRegisterForm(email, username, password);

        //click register
        getRegisterPage().getRegisterBtn().click();

        waitContentLoadByWebelement(getLogInPage().getSignInBtn());

        //Try to sign
        getLogInPage().signIn(email, password);

        //Assert that there is text "BudgetActivity" and Add expense button
        waitContentLoadByWebelement(getBudgetActivityPage().getBudgetActivity());

        //Check that there are any element with "BudgetActivity" text
        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(getBudgetActivityPage().getBudgetActivity(), "Budget Activity message was not found");

        //Check that there are button Add Expense
        soft.assertTrue(getBudgetActivityPage().getAddExpenseBtn().isDisplayed(),
            "Add expense button was not found");

        soft.assertAll();
    }
}
