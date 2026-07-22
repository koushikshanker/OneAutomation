package com.oneautomation.tests.stepdefinitions.dashboard;

import com.oneautomation.tests.pageobjects.dashboard.DashboardPO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DashboardStepDef {

    private final DashboardPO dashboardPage = new DashboardPO();

    @Then("the dashboard grid should be loaded")
    public void theDashboardGridShouldBeLoaded() {
        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "Dashboard grid should be visible");
    }

    @Then("the dashboard URL should contain {string}")
    public void theDashboardUrlShouldContain(String expectedPath) {
        String currentUrl = dashboardPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(expectedPath),
                "URL should contain '" + expectedPath + "' but was: " + currentUrl);
    }

    @Then("the {string} widget should be visible on dashboard")
    public void theWidgetShouldBeVisibleOnDashboard(String widgetTitle) {
        Assert.assertTrue(dashboardPage.isWidgetPresent(widgetTitle),
                widgetTitle + " widget should be visible on the dashboard");
    }

    @Then("the Time at Work widget should be displayed")
    public void theTimeAtWorkWidgetShouldBeDisplayed() {
        Assert.assertTrue(dashboardPage.isTimeAtWorkVisible(),
                "Time at Work widget should be visible");
    }

    @Then("the Employees on Leave widget should be displayed")
    public void theEmployeesOnLeaveWidgetShouldBeDisplayed() {
        Assert.assertTrue(dashboardPage.isEmployeesOnLeaveVisible(),
                "Employees on Leave Today widget should be visible");
    }

    @Then("the quick launch section should have items")
    public void theQuickLaunchSectionShouldHaveItems() {
        int count = dashboardPage.getQuickLaunchCount();
        Assert.assertTrue(count > 0,
                "Quick launch section should have at least one item, found: " + count);
    }

    @When("the user clicks on quick launch {string}")
    public void theUserClicksOnQuickLaunch(String title) {
        dashboardPage.clickQuickLaunch(title);
    }
}
