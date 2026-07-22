package com.oneautomation.tests.stepdefinitions.myinfo;

import com.oneautomation.tests.pageobjects.myinfo.PersonalDetailsPO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class MyInfoStepDef {

    private final PersonalDetailsPO personalDetails = new PersonalDetailsPO();

    @Then("the Personal Details page should be displayed")
    public void thePersonalDetailsPageShouldBeDisplayed() {
        Assert.assertTrue(personalDetails.isPersonalDetailsPageDisplayed(),
                "Personal Details page should be displayed");
    }

    @Then("the first name field should not be empty")
    public void theFirstNameFieldShouldNotBeEmpty() {
        String firstName = personalDetails.getFirstName();
        Assert.assertNotNull(firstName, "First name should not be null");
        Assert.assertFalse(firstName.isEmpty(), "First name should not be empty");
    }

    @Then("the last name field should not be empty")
    public void theLastNameFieldShouldNotBeEmpty() {
        String lastName = personalDetails.getLastName();
        Assert.assertNotNull(lastName, "Last name should not be null");
        Assert.assertFalse(lastName.isEmpty(), "Last name should not be empty");
    }

    @When("the user updates middle name to {string}")
    public void theUserUpdatesMiddleName(String middleName) {
        personalDetails.updateMiddleName(middleName);
    }

    @When("the user clicks Save on personal details")
    public void theUserClicksSaveOnPersonalDetails() {
        personalDetails.clickSave();
    }

    @Then("the personal details toast should be displayed")
    public void thePersonalDetailsToastShouldBeDisplayed() {
        Assert.assertTrue(personalDetails.isToastDisplayed(),
                "Toast notification should be displayed after saving");
    }

    @When("the user navigates to Contact Details tab")
    public void theUserNavigatesToContactDetailsTab() {
        personalDetails.goToContactDetails();
    }

    @Then("the profile image should be displayed")
    public void theProfileImageShouldBeDisplayed() {
        Assert.assertTrue(personalDetails.isProfileImageDisplayed(),
                "Profile image should be visible");
    }
}
