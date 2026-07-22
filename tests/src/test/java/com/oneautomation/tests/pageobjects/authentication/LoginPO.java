package com.oneautomation.tests.pageobjects.authentication;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class LoginPO extends WebBase {

    private final Locator usernameField = Locator.cssSelector("input[name='username']", "Username input");
    private final Locator passwordField = Locator.cssSelector("input[name='password']", "Password input");
    private final Locator loginButton = Locator.cssSelector("button[type='submit']", "Login button");
    private final Locator errorAlert = Locator.cssSelector(".oxd-alert-content-text", "Login error alert");
    private final Locator requiredMessage = Locator.cssSelector(".oxd-input-field-error-message", "Required field message");
    private final Locator forgotPasswordLink = Locator.cssSelector(".orangehrm-login-forgot-header", "Forgot password link");
    private final Locator loginLogo = Locator.cssSelector(".orangehrm-login-branding img", "OrangeHRM logo");

    public void enterUsername(String username) {
        wait.waitForVisibilityOfElement(usernameField);
        genericMethod.sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        genericMethod.sendKeys(passwordField, password);
    }

    public void clickLogin() {
        genericMethod.click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        wait.waitForVisibilityOfElement(errorAlert);
        return genericMethod.getText(errorAlert);
    }

    public boolean isRequiredMessageDisplayed() {
        return genericMethod.isDisplayed(requiredMessage);
    }

    public boolean isLoginPageDisplayed() {
        return genericMethod.isDisplayed(loginButton);
    }

    public boolean isLogoDisplayed() {
        return genericMethod.isDisplayed(loginLogo);
    }

    public void clickForgotPassword() {
        genericMethod.click(forgotPasswordLink);
    }
}
