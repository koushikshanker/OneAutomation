package com.oneautomation.tests.pageobjects.pim;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class EmployeeListPO extends WebBase {

    private final Locator addButton = Locator.xpath("//button[normalize-space()='Add']", "Add Employee button");
    private final Locator searchButton = Locator.xpath("//button[normalize-space()='Search']", "Search button");
    private final Locator resetButton = Locator.xpath("//button[normalize-space()='Reset']", "Reset button");
    private final Locator employeeNameInput = Locator.cssSelector(
            ".oxd-autocomplete-text-input input", "Employee name autocomplete");
    private final Locator employeeIdInput = Locator.xpath(
            "(//input[contains(@class,'oxd-input')])[2]", "Employee ID input");
    private final Locator tableRows = Locator.cssSelector(".oxd-table-body .oxd-table-row", "Table rows");
    private final Locator recordsCount = Locator.cssSelector(
            ".orangehrm-horizontal-padding span", "Records found text");
    private final Locator editIcon = Locator.cssSelector(".oxd-icon-button--info", "Edit icon");
    private final Locator deleteIcon = Locator.cssSelector(".oxd-icon-button--danger", "Delete icon");
    private final Locator checkboxAll = Locator.cssSelector(
            ".oxd-table-header .oxd-checkbox-input", "Select all checkbox");
    private final Locator deleteSelectedButton = Locator.xpath(
            "//button[normalize-space()='Delete Selected']", "Delete Selected button");
    private final Locator confirmDeleteButton = Locator.xpath(
            "//button[normalize-space()='Yes, Delete']", "Confirm delete button");
    private final Locator toastMessage = Locator.cssSelector(".oxd-toast", "Toast notification");
    private final Locator autocompleteOption = Locator.cssSelector(
            ".oxd-autocomplete-option", "Autocomplete dropdown option");
    private final Locator noRecordsText = Locator.xpath(
            "//span[normalize-space()='No Records Found']", "No records found");

    public void clickAdd() {
        wait.waitForElementToBeClickable(addButton);
        genericMethod.click(addButton);
    }

    public void searchByEmployeeName(String name) {
        wait.waitForVisibilityOfElement(employeeNameInput);
        genericMethod.sendKeys(employeeNameInput, name);
        wait.hardWait(2);
        if (genericMethod.isDisplayed(autocompleteOption)) {
            genericMethod.click(autocompleteOption);
        }
        genericMethod.click(searchButton);
    }

    public void searchByEmployeeId(String id) {
        wait.waitForVisibilityOfElement(employeeIdInput);
        genericMethod.sendKeys(employeeIdInput, id);
        genericMethod.click(searchButton);
    }

    public void clickReset() {
        genericMethod.click(resetButton);
    }

    public int getTableRowCount() {
        wait.hardWait(1);
        return genericMethod.getElements(tableRows).size();
    }

    public String getRecordsCountText() {
        wait.waitForVisibilityOfElement(recordsCount);
        return genericMethod.getText(recordsCount);
    }

    public void clickFirstEditIcon() {
        wait.waitForElementToBeClickable(editIcon);
        genericMethod.click(editIcon);
    }

    public void deleteFirstEmployee() {
        wait.waitForElementToBeClickable(deleteIcon);
        genericMethod.click(deleteIcon);
        wait.waitForElementToBeClickable(confirmDeleteButton);
        genericMethod.click(confirmDeleteButton);
    }

    public boolean isToastDisplayed() {
        return genericMethod.isDisplayed(toastMessage);
    }

    public boolean isNoRecordsFound() {
        return genericMethod.isDisplayed(noRecordsText);
    }
}
