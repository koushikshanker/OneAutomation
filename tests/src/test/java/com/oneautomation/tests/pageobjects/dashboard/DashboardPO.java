package com.oneautomation.tests.pageobjects.dashboard;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class DashboardPO extends WebBase {

    private final Locator dashboardGrid = Locator.cssSelector(".orangehrm-dashboard-grid", "Dashboard grid");
    private final Locator widgetByTitle = Locator.xpath(
            "//p[contains(@class,'oxd-text') and normalize-space(.)='${0}']", "Dashboard widget title");
    private final Locator quickLaunchCards = Locator.cssSelector(".orangehrm-quick-launch-card", "Quick launch cards");
    private final Locator quickLaunchByTitle = Locator.xpath(
            "//button[contains(@class,'orangehrm-quick-launch-card')]//p[normalize-space(.)='${0}']", "Quick launch by title");
    private final Locator timeAtWork = Locator.xpath(
            "//p[normalize-space(.)='Time at Work']", "Time at Work widget");
    private final Locator employeesOnLeave = Locator.xpath(
            "//p[normalize-space(.)='Employees on Leave Today']", "Employees on Leave widget");

    public boolean isDashboardLoaded() {
        return genericMethod.isDisplayed(dashboardGrid);
    }

    public boolean isWidgetPresent(String widgetTitle) {
        Locator widget = genericMethod.parameterizedLocator(widgetByTitle, widgetTitle);
        return genericMethod.isDisplayed(widget);
    }

    public int getQuickLaunchCount() {
        return genericMethod.getElements(quickLaunchCards).size();
    }

    public void clickQuickLaunch(String title) {
        Locator card = genericMethod.parameterizedLocator(quickLaunchByTitle, title);
        wait.waitForElementToBeClickable(card);
        genericMethod.click(card);
    }

    public boolean isTimeAtWorkVisible() {
        return genericMethod.isDisplayed(timeAtWork);
    }

    public boolean isEmployeesOnLeaveVisible() {
        return genericMethod.isDisplayed(employeesOnLeave);
    }

    public String getCurrentUrl() {
        return genericMethod.fe().getCurrentURL();
    }
}
