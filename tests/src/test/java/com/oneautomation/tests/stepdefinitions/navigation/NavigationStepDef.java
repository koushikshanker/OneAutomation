package com.oneautomation.tests.stepdefinitions.navigation;

import com.oneautomation.tests.pageobjects.common.SidebarPO;
import com.oneautomation.tests.pageobjects.common.TopbarPO;
import com.oneautomation.tests.pageobjects.authentication.LoginPO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class NavigationStepDef {

    private final SidebarPO sidebar = new SidebarPO();
    private final TopbarPO topbar = new TopbarPO();
    private final LoginPO loginPage = new LoginPO();

    @When("the user navigates to {string} from sidebar")
    public void theUserNavigatesToModule(String moduleName) {
        sidebar.navigateTo(moduleName);
    }

    @Then("the sidebar should be visible")
    public void theSidebarShouldBeVisible() {
        Assert.assertTrue(sidebar.isSidebarVisible(),
                "Sidebar should be visible");
    }

    @When("the user searches for {string} in sidebar")
    public void theUserSearchesInSidebar(String text) {
        sidebar.searchMenu(text);
    }

    @When("the user toggles sidebar collapse")
    public void theUserTogglesSidebarCollapse() {
        sidebar.toggleCollapse();
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        String actualTitle = topbar.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle,
                "Page title mismatch");
    }

    @When("the user clicks on the user dropdown")
    public void theUserClicksOnUserDropdown() {
        topbar.clickUserDropdown();
    }

    @When("the user logs out")
    public void theUserLogsOut() {
        topbar.logout();
    }

    @Then("the user should be redirected to login page")
    public void theUserShouldBeRedirectedToLoginPage() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "User should be redirected to login page after logout");
    }

    @When("the user clicks About in user menu")
    public void theUserClicksAboutInUserMenu() {
        topbar.clickAbout();
    }

    @When("the user clicks topbar tab {string}")
    public void theUserClicksTopbarTab(String tabName) {
        topbar.clickTopbarTab(tabName);
    }
}
