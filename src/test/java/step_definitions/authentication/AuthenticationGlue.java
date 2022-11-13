package step_definitions.authentication;

import base.Base;
import com.parabank.page_objects.AccountOverviewPage;
import com.parabank.page_objects.AccountServicesPage;
import com.parabank.page_objects.Homepage;
import com.parabank.page_objects.RegistrationPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import utils.DataFaker;

public class AuthenticationGlue extends Base {

    Homepage homepage = new Homepage();
    RegistrationPage registrationPage;
    AccountOverviewPage accountOverviewPage;
    AccountServicesPage accountServicesPage;

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

    @When("user clicks register button")
    public void user_clicks_register_button() {
        registrationPage = homepage.clickRegisterNewAccountButton();
    }

    @When("user fills out account registration form")
    public void user_fills_out_account_registration_form() {
        DataFaker faker = new DataFaker();

        String firstName = faker.firstName();
        String lastName = faker.lastName();
        String streetAddress = faker.streetAddress();
        String city = faker.city();
        String state = faker.state();
        String zip = faker.zipcode();
        String phoneNumber = faker.mobilePhone();
        String ssn = faker.ssn();
        String username = faker.email().split("@")[0];
        String password = faker.password();

        registrationPage.fillRegistrationAccountForm(firstName, lastName, streetAddress, city, state, zip, phoneNumber,
                ssn, username, password);
    }

    @When("user clicks registration form submit button")
    public void user_clicks_registration_form_submit_button() {
        accountServicesPage = registrationPage.submitAccountRegistrationForm();
    }

    @Then("user is navigated to Account Services page")
    public void user_is_navigated_to_account_services_page() {
        if (isElementInvisible(registrationPage.accountRegistrationForm)) {
            String expectedMessage = "Your account was created successfully. You are now logged in.";
            Assert.assertEquals(expectedMessage, accountServicesPage.getAccountRegistrationMessage());
        }
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
