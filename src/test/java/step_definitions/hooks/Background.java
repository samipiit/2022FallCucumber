package step_definitions.hooks;

import base.Base;
import io.cucumber.java.en.Given;

public class Background extends Base {

    @Given("user navigates to ParaBank application")
    public void user_navigates_to_para_bank_application() {
        driver.get(url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
}
