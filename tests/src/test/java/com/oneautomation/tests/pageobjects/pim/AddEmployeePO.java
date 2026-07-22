package com.oneautomation.tests.pageobjects.pim;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class AddEmployeePO extends WebBase {

    private final Locator firstNameInput = Locator.cssSelector("input[name='firstName']", "First name");
    private final Locator middleNameInput = Locator.cssSelector("input[name='middleName']", "Middle name");
    private final Locator lastNameInput = Locator.cssSelector("input[name='lastName']", "Last name");
    private final Locator employeeIdInput = Locator.xpath(
            "(//input[contains(@class,'oxd-input')])[2]", "Employee ID");
    private final Locator createLoginToggle = Locator.cssSelector(".oxd-switch-input", "Create login toggle");
    private final Locator saveButton = Locator.xpath("//button[normalize-space()='Save']", "Save button");
    private final Locator toastMessage = Locator.cssSelector(".oxd-toast", "Toast notification");
    private final Locator toastText = Locator.cssSelector(".oxd-toast-content-text", "Toast text");
    private final Locator usernameInput = Locator.xpath(
            "(//input[contains(@class,'oxd-input')])[5]", "Login username");
    private final Locator passwordInput = Locator.xpath(
            "(//input[contains(@class,'oxd-input')])[6]", "Login password");
    private final Locator confirmPasswordInput = Locator.xpath(
            "(//input[contains(@class,'oxd-input')])[7]", "Confirm password");

    public void enterFirstName(String firstName) {
        wait.waitForVisibilityOfElement(firstNameInput);
        genericMethod.sendKeys(firstNameInput, firstName);
    }

    public void enterMiddleName(String middleName) {
        genericMethod.sendKeys(middleNameInput, middleName);
    }

    public void enterLastName(String lastName) {
        genericMethod.sendKeys(lastNameInput, lastName);
    }

    public String getEmployeeId() {
        return genericMethod.getAttribute(employeeIdInput, "value");
    }

    public void toggleCreateLogin() {
        jsUtil.click(createLoginToggle);
    }

    public void enterLoginCredentials(String username, String password) {
        toggleCreateLogin();
        wait.waitForVisibilityOfElement(usernameInput);
        genericMethod.sendKeys(usernameInput, username);
        genericMethod.sendKeys(passwordInput, password);
        genericMethod.sendKeys(confirmPasswordInput, password);
    }

    public void clickSave() {
        wait.waitForElementToBeClickable(saveButton);
        genericMethod.click(saveButton);
    }

    public void addEmployee(String firstName, String lastName) {
        enterFirstName(firstName);
        enterLastName(lastName);
        clickSave();
    }

    public void addEmployeeWithMiddleName(String firstName, String middleName, String lastName) {
        enterFirstName(firstName);
        enterMiddleName(middleName);
        enterLastName(lastName);
        clickSave();
    }

    public boolean isToastDisplayed() {
        wait.waitForVisibilityOfElement(toastMessage, 10);
        return genericMethod.isDisplayed(toastMessage);
    }

    public String getToastText() {
        wait.waitForVisibilityOfElement(toastText, 10);
        return genericMethod.getText(toastText);
    }
}
