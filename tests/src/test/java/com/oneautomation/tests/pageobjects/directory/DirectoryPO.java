package com.oneautomation.tests.pageobjects.directory;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class DirectoryPO extends WebBase {

    private final Locator employeeNameInput = Locator.cssSelector(
            ".oxd-autocomplete-text-input input", "Employee name autocomplete");
    private final Locator jobTitleDropdown = Locator.xpath(
            "(//div[contains(@class,'oxd-select-text-input')])[1]", "Job title dropdown");
    private final Locator locationDropdown = Locator.xpath(
            "(//div[contains(@class,'oxd-select-text-input')])[2]", "Location dropdown");
    private final Locator searchButton = Locator.xpath("//button[normalize-space()='Search']", "Search");
    private final Locator resetButton = Locator.xpath("//button[normalize-space()='Reset']", "Reset");
    private final Locator employeeCards = Locator.cssSelector(
            ".orangehrm-directory-card", "Employee cards");
    private final Locator directoryHeader = Locator.xpath(
            "//h6[normalize-space(.)='Directory']", "Directory header");
    private final Locator autocompleteOption = Locator.cssSelector(
            ".oxd-autocomplete-option", "Autocomplete option");
    private final Locator noRecordsText = Locator.xpath(
            "//span[normalize-space()='No Records Found']", "No records");

    public boolean isDirectoryPageDisplayed() {
        return genericMethod.isDisplayed(directoryHeader);
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

    public void clickReset() {
        genericMethod.click(resetButton);
    }

    public int getEmployeeCardCount() {
        wait.hardWait(1);
        return genericMethod.getElements(employeeCards).size();
    }

    public boolean isNoRecordsFound() {
        return genericMethod.isDisplayed(noRecordsText);
    }
}
