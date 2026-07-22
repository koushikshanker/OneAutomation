package com.oneautomation.tests.pageobjects.leave;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class ApplyLeavePO extends WebBase {

    private final Locator leaveTypeDropdown = Locator.cssSelector(
            ".oxd-select-text-input", "Leave type dropdown");
    private final Locator fromDateInput = Locator.xpath(
            "(//input[contains(@class,'oxd-input')])[2]", "From date");
    private final Locator toDateInput = Locator.xpath(
            "(//input[contains(@class,'oxd-input')])[3]", "To date");
    private final Locator commentsTextarea = Locator.cssSelector(
            "textarea.oxd-textarea", "Comments");
    private final Locator applyButton = Locator.xpath(
            "//button[normalize-space()='Apply']", "Apply button");
    private final Locator toastMessage = Locator.cssSelector(".oxd-toast", "Toast");
    private final Locator applyLeaveHeader = Locator.xpath(
            "//h6[normalize-space(.)='Apply Leave']", "Apply Leave header");
    private final Locator dropdownOption = Locator.xpath(
            "(//div[@role='option'])[2]", "Second dropdown option");
    private final Locator leaveListTab = Locator.xpath(
            "//a[normalize-space(.)='My Leave']", "My Leave tab");
    private final Locator leaveListTable = Locator.cssSelector(".oxd-table", "Leave list table");

    public boolean isApplyLeavePageDisplayed() {
        return genericMethod.isDisplayed(applyLeaveHeader);
    }

    public void selectLeaveType() {
        wait.waitForElementToBeClickable(leaveTypeDropdown);
        genericMethod.click(leaveTypeDropdown);
        wait.waitForElementToBeClickable(dropdownOption);
        genericMethod.click(dropdownOption);
    }

    public void enterFromDate(String date) {
        wait.waitForVisibilityOfElement(fromDateInput);
        genericMethod.clearText(fromDateInput);
        genericMethod.sendKeys(fromDateInput, date);
    }

    public void enterToDate(String date) {
        wait.waitForVisibilityOfElement(toDateInput);
        genericMethod.clearText(toDateInput);
        genericMethod.sendKeys(toDateInput, date);
    }

    public void enterComments(String comments) {
        genericMethod.sendKeys(commentsTextarea, comments);
    }

    public void clickApply() {
        wait.waitForElementToBeClickable(applyButton);
        genericMethod.click(applyButton);
    }

    public boolean isToastDisplayed() {
        return genericMethod.isDisplayed(toastMessage);
    }

    public void goToMyLeaveList() {
        wait.waitForElementToBeClickable(leaveListTab);
        genericMethod.click(leaveListTab);
    }

    public boolean isLeaveListTableDisplayed() {
        return genericMethod.isDisplayed(leaveListTable);
    }
}
