package com.oneautomation.tests.pageobjects.admin;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class UserManagementPO extends WebBase {

    private final Locator usernameSearch = Locator.xpath(
            "(//input[contains(@class,'oxd-input')])[2]", "Username search");
    private final Locator userRoleDropdown = Locator.xpath(
            "(//div[contains(@class,'oxd-select-text-input')])[1]", "User role dropdown");
    private final Locator statusDropdown = Locator.xpath(
            "(//div[contains(@class,'oxd-select-text-input')])[2]", "Status dropdown");
    private final Locator employeeNameInput = Locator.cssSelector(
            ".oxd-autocomplete-text-input input", "Employee name autocomplete");
    private final Locator searchButton = Locator.xpath("//button[normalize-space()='Search']", "Search");
    private final Locator resetButton = Locator.xpath("//button[normalize-space()='Reset']", "Reset");
    private final Locator addButton = Locator.xpath("//button[normalize-space()='Add']", "Add user");
    private final Locator tableRows = Locator.cssSelector(".oxd-table-body .oxd-table-row", "Table rows");
    private final Locator recordsCount = Locator.cssSelector(
            ".orangehrm-horizontal-padding span", "Records count");
    private final Locator deleteIcon = Locator.cssSelector(".oxd-icon-button--danger", "Delete icon");
    private final Locator confirmDeleteButton = Locator.xpath(
            "//button[normalize-space()='Yes, Delete']", "Confirm delete");
    private final Locator toastMessage = Locator.cssSelector(".oxd-toast", "Toast");
    private final Locator dropdownOption = Locator.xpath(
            "//div[@role='option']//span[normalize-space(.)='${0}']", "Dropdown option");
    private final Locator pageHeader = Locator.xpath(
            "//h5[normalize-space(.)='System Users']", "System Users header");

    public boolean isSystemUsersPageDisplayed() {
        return genericMethod.isDisplayed(pageHeader);
    }

    public void searchByUsername(String username) {
        wait.waitForVisibilityOfElement(usernameSearch);
        genericMethod.sendKeys(usernameSearch, username);
        genericMethod.click(searchButton);
    }

    public void selectDropdownOption(String optionText) {
        Locator option = genericMethod.parameterizedLocator(dropdownOption, optionText);
        wait.waitForElementToBeClickable(option);
        genericMethod.click(option);
    }

    public void filterByUserRole(String role) {
        genericMethod.click(userRoleDropdown);
        selectDropdownOption(role);
    }

    public void filterByStatus(String status) {
        genericMethod.click(statusDropdown);
        selectDropdownOption(status);
    }

    public void clickSearch() {
        genericMethod.click(searchButton);
    }

    public void clickReset() {
        genericMethod.click(resetButton);
    }

    public void clickAdd() {
        genericMethod.click(addButton);
    }

    public int getTableRowCount() {
        wait.hardWait(1);
        return genericMethod.getElements(tableRows).size();
    }

    public String getRecordsCountText() {
        wait.waitForVisibilityOfElement(recordsCount);
        return genericMethod.getText(recordsCount);
    }

    public void deleteFirstUser() {
        wait.waitForElementToBeClickable(deleteIcon);
        genericMethod.click(deleteIcon);
        wait.waitForElementToBeClickable(confirmDeleteButton);
        genericMethod.click(confirmDeleteButton);
    }

    public boolean isToastDisplayed() {
        return genericMethod.isDisplayed(toastMessage);
    }
}
