package step_definitions.authentication;

import base.Base;
import com.parabank.page_objects.AccountOverviewPage;
import com.parabank.page_objects.Homepage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class AuthenticationGlue extends Base {

    Homepage homepage = new Homepage();
    AccountOverviewPage accountOverviewPage;

    @When("user enters {string} in username input field")
    public void userEntersInUsernameInputField(String username) {
        homepage.inputUsername(username);
    }

    @When("user enters {string} in password input field")
    public void userEntersInPasswordInputField(String password) {
        homepage.inputPassword(password);
    }
    @When("user clicks login button")
    public void user_clicks_login_button() {
        accountOverviewPage = homepage.clickSubmitLogin();
    }

    @Then("user is navigated to Account Overview page")
    public void user_is_navigated_to_account_overview_page() {
        String expectedLoggedInAs = config.getProperty("account_fname") + " " + config.getProperty("account_lname");
        String actualLoggedInAs = accountOverviewPage.getLoggedInAs();

        Assert.assertTrue(actualLoggedInAs.contains(expectedLoggedInAs));
    }

    @Then("error message should be visible")
    public void error_message_should_be_visible() {
        Assert.assertTrue(isElementVisible(homepage.errorMessage));
    }
}
