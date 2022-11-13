package com.parabank.page_objects;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends Base {

    @FindBy (id = "customerForm")
    public WebElement accountRegistrationForm;

    @FindBy (id = "customer.firstName")
    public WebElement firstNameInputField;

    @FindBy (id = "customer.lastName")
    public WebElement lastNameInputField;

    @FindBy (id = "customer.address.street")
    public WebElement streetAddressInputField;

    @FindBy (id = "customer.address.city")
    public WebElement cityInputField;

    @FindBy (id = "customer.address.state")
    public WebElement stateInputField;

    @FindBy (id = "customer.address.zipCode")
    public WebElement zipCodeInputField;

    @FindBy (id = "customer.phoneNumber")
    public WebElement phoneNumberInputField;

    @FindBy (id = "customer.ssn")
    public WebElement ssnInputField;

    @FindBy (id = "customer.username")
    public WebElement usernameInputField;

    @FindBy (id = "customer.password")
    public WebElement passwordInputField;

    @FindBy (id = "repeatedPassword")
    public WebElement confirmPasswordInputField;

    @FindBy (xpath = "//input[@value='Register']")
    public WebElement registerButton;

    public RegistrationPage() {
        PageFactory.initElements(driver, this);
    }

    public void inputFirstName(String firstName) {
        sendKeysToElement(firstNameInputField, firstName);
    }

    public void inputLastName(String lastName) {
        sendKeysToElement(lastNameInputField, lastName);
    }

    public void inputStreetAddress(String streetAddress) {
        sendKeysToElement(streetAddressInputField, streetAddress);
    }

    public void inputCity(String city) {
        sendKeysToElement(cityInputField, city);
    }

    public void inputState(String state) {
        sendKeysToElement(stateInputField, state);
    }

    public void inputZipCode(String zipCode) {
        sendKeysToElement(zipCodeInputField, zipCode);
    }

    public void inputPhoneNumber(String phoneNumber) {
        sendKeysToElement(phoneNumberInputField, phoneNumber);
    }

    public void inputSSN(String ssn) {
        sendKeysToElement(ssnInputField, ssn);
    }

    public void inputUsername(String username) {
        sendKeysToElement(usernameInputField, username);
    }

    public void inputPassword(String password) {
        sendKeysToElement(passwordInputField, password);
    }

    public void inputPasswordConfirm(String password) {
        sendKeysToElement(confirmPasswordInputField, password);
    }

    public AccountServicesPage clickRegisterButton() {
        clickOnElement(registerButton);

        return new AccountServicesPage();
    }

    public void fillRegistrationAccountForm(String firstName, String lastName, String streetAddress, String city, String state,
                                            String zipCode, String phoneNumber, String ssn, String username, String password) {
        inputFirstName(firstName);
        inputLastName(lastName);
        inputStreetAddress(streetAddress);
        inputCity(city);
        inputState(state);
        inputZipCode(zipCode);
        inputPhoneNumber(phoneNumber);
        inputSSN(ssn);
        inputUsername(username);
        inputPassword(password);
        inputPasswordConfirm(password);
    }

    public AccountServicesPage submitAccountRegistrationForm() {
        return clickRegisterButton();
    }

}
