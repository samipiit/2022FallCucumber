package com.parabank.page_objects;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountServicesPage extends Base {

    @FindBy(css = "#rightPanel p")
    public WebElement accountRegistrationMessageText;

    public AccountServicesPage() {
        PageFactory.initElements(driver, this);
    }

    public String getAccountRegistrationMessage() {
        return getTrimmedElementText(accountRegistrationMessageText);
    }
}
