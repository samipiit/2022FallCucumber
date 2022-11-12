package com.parabank.page_objects;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountOverviewPage extends Base {

    @FindBy (css = "#leftPanel p")
    public WebElement accountNameHeader;

    public AccountOverviewPage() {
        PageFactory.initElements(driver, this);
    }

    public String getLoggedInAs() {
        return getTrimmedElementText(accountNameHeader);
    }
}
