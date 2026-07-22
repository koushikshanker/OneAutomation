package com.oneautomation.tests.pageobjects.common;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class SidebarPO extends WebBase {

    private final Locator menuItemByName = Locator.xpath(
            "//span[contains(@class,'oxd-main-menu-item--name') and normalize-space(.)='${0}']", "Sidebar menu item");
    private final Locator sidebar = Locator.cssSelector(".oxd-sidepanel", "Sidebar panel");
    private final Locator sidebarSearchInput = Locator.xpath("//input[@placeholder='Search']", "Sidebar search");
    private final Locator collapseToggle = Locator.cssSelector(".oxd-sidepanel-toggle", "Sidebar collapse toggle");

    public void navigateTo(String moduleName) {
        Locator menuItem = genericMethod.parameterizedLocator(menuItemByName, moduleName);
        wait.waitForElementToBeClickable(menuItem);
        genericMethod.click(menuItem);
    }

    public boolean isSidebarVisible() {
        return genericMethod.isDisplayed(sidebar);
    }

    public void searchMenu(String text) {
        wait.waitForVisibilityOfElement(sidebarSearchInput);
        genericMethod.sendKeys(sidebarSearchInput, text);
    }

    public void toggleCollapse() {
        genericMethod.click(collapseToggle);
    }
}
