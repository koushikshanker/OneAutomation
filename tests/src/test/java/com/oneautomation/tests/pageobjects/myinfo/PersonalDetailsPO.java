package com.oneautomation.tests.pageobjects.myinfo;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class PersonalDetailsPO extends WebBase {

    private final Locator firstNameInput = Locator.cssSelector("input[name='firstName']", "First name");
    private final Locator lastNameInput = Locator.cssSelector("input[name='lastName']", "Last name");
    private final Locator middleNameInput = Locator.cssSelector("input[name='middleName']", "Middle name");
    private final Locator nationalityDropdown = Locator.xpath(
            "(//div[contains(@class,'oxd-select-text-input')])[1]", "Nationality dropdown");
    private final Locator saveButton = Locator.xpath(
            "(//button[normalize-space()='Save'])[1]", "First Save button");
    private final Locator toastMessage = Locator.cssSelector(".oxd-toast", "Toast");
    private final Locator personalDetailsHeader = Locator.xpath(
            "//h6[normalize-space(.)='Personal Details']", "Personal Details header");
    private final Locator contactDetailsTab = Locator.xpath(
            "//a[normalize-space(.)='Contact Details']", "Contact Details tab");
    private final Locator profileImage = Locator.cssSelector(
            ".orangehrm-edit-employee-image img", "Profile image");

    public boolean isPersonalDetailsPageDisplayed() {
        return genericMethod.isDisplayed(personalDetailsHeader);
    }

    public String getFirstName() {
        return genericMethod.getAttribute(firstNameInput, "value");
    }

    public String getLastName() {
        return genericMethod.getAttribute(lastNameInput, "value");
    }

    public void updateMiddleName(String middleName) {
        wait.waitForVisibilityOfElement(middleNameInput);
        genericMethod.clearText(middleNameInput);
        genericMethod.sendKeys(middleNameInput, middleName);
    }

    public void clickSave() {
        wait.waitForElementToBeClickable(saveButton);
        genericMethod.click(saveButton);
    }

    public boolean isToastDisplayed() {
        wait.waitForVisibilityOfElement(toastMessage, 10);
        return genericMethod.isDisplayed(toastMessage);
    }

    public void goToContactDetails() {
        wait.waitForElementToBeClickable(contactDetailsTab);
        genericMethod.click(contactDetailsTab);
    }

    public boolean isProfileImageDisplayed() {
        return genericMethod.isDisplayed(profileImage);
    }
}
