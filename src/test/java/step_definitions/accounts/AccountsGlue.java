package step_definitions.accounts;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountsGlue extends Base {


    @Given("this is given")
    public void thisIsGiven() {
        System.out.println("GIVEN");
    }

    @When("this is an action")
    public void thisIsAnAction() {
        System.out.println("WHEN");
    }

    @Then("this is an assertion")
    public void thisIsAnAssertion() {
        System.out.println("THEN");
    }

}
