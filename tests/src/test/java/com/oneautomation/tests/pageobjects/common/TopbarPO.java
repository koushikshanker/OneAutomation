package com.oneautomation.tests.pageobjects.common;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class TopbarPO extends WebBase {

    private final Locator userDropdown = Locator.cssSelector(".oxd-userdropdown-tab", "User dropdown");
    private final Locator logoutLink = Locator.xpath("//a[normalize-space()='Logout']", "Logout link");
    private final Locator aboutLink = Locator.xpath("//a[normalize-space()='About']", "About link");
    private final Locator changePasswordLink = Locator.xpath("//a[normalize-space()='Change Password']", "Change Password link");
    private final Locator topbarHeader = Locator.cssSelector(".oxd-topbar-header-title", "Topbar header");
    private final Locator pageTitle = Locator.cssSelector(".oxd-topbar-header-breadcrumb h6", "Page title breadcrumb");
    private final Locator topbarNavTab = Locator.xpath(
            "//a[contains(@class,'oxd-topbar-body-nav-tab-item') and normalize-space(.)='${0}']", "Topbar nav tab");

    public void clickUserDropdown() {
        wait.waitForElementToBeClickable(userDropdown);
        genericMethod.click(userDropdown);
    }

    public void logout() {
        clickUserDropdown();
        wait.waitForElementToBeClickable(logoutLink);
        genericMethod.click(logoutLink);
    }

    public void clickAbout() {
        clickUserDropdown();
        wait.waitForElementToBeClickable(aboutLink);
        genericMethod.click(aboutLink);
    }

    public String getPageTitle() {
        wait.waitForVisibilityOfElement(pageTitle);
        return genericMethod.getText(pageTitle);
    }

    public void clickTopbarTab(String tabName) {
        Locator tab = genericMethod.parameterizedLocator(topbarNavTab, tabName);
        wait.waitForElementToBeClickable(tab);
        genericMethod.click(tab);
    }

    public boolean isUserDropdownVisible() {
        return genericMethod.isDisplayed(userDropdown);
    }
}
