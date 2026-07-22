package com.oneautomation.tests.stepdefinitions.authentication;

import com.oneautomation.tests.common.EndpointResolver;
import com.oneautomation.tests.pageobjects.authentication.LoginPO;
import com.oneautomation.tests.pageobjects.dashboard.DashboardPO;
import com.oneautomation.tests.pageobjects.common.TopbarPO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class LoginStepDef {

    private final LoginPO loginPage = new LoginPO();
    private final DashboardPO dashboardPage = new DashboardPO();
    private final TopbarPO topbar = new TopbarPO();

    @Given("the user is on the OrangeHRM login page")
    public void theUserIsOnTheOrangeHRMLoginPage() {
        loginPage.genericMethod.fe().goToPageURL(
                EndpointResolver.getWebURL("/web/index.php/auth/login"));
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithCredentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the dashboard should be displayed")
    public void theDashboardShouldBeDisplayed() {
        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "Dashboard should be displayed after successful login");
    }

    @Then("the user should see login error {string}")
    public void theUserShouldSeeLoginError(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Then("the required field validation should be displayed")
    public void theRequiredFieldValidationShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isRequiredMessageDisplayed(),
                "Required field validation message should be visible");
    }

    @Then("the login page should be visible")
    public void theLoginPageShouldBeVisible() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should be displayed");
    }

    @Then("the OrangeHRM logo should be displayed on login page")
    public void theOrangeHRMLogoShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isLogoDisplayed(),
                "OrangeHRM logo should be visible on login page");
    }

    @When("the user clicks forgot password link")
    public void theUserClicksForgotPasswordLink() {
        loginPage.clickForgotPassword();
    }

    @When("the user logs in with valid admin credentials")
    public void theUserLogsInWithValidAdminCredentials() {
        loginPage.login("Admin", "admin123");
    }

    @When("the user submits login without entering credentials")
    public void theUserSubmitsLoginWithoutCredentials() {
        loginPage.clickLogin();
    }

    @Then("the user dropdown should be visible in topbar")
    public void theUserDropdownShouldBeVisible() {
        Assert.assertTrue(topbar.isUserDropdownVisible(),
                "User dropdown should be visible after login");
    }
}
