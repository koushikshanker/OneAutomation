package com.oneautomation.tests.stepdefinitions.leave;

import com.oneautomation.tests.pageobjects.leave.ApplyLeavePO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LeaveStepDef {

    private final ApplyLeavePO applyLeave = new ApplyLeavePO();

    @Then("the Apply Leave page should be displayed")
    public void theApplyLeavePageShouldBeDisplayed() {
        Assert.assertTrue(applyLeave.isApplyLeavePageDisplayed(),
                "Apply Leave page should be displayed");
    }

    @When("the user selects a leave type")
    public void theUserSelectsALeaveType() {
        applyLeave.selectLeaveType();
    }

    @When("the user enters from date {string}")
    public void theUserEntersFromDate(String date) {
        applyLeave.enterFromDate(date);
    }

    @When("the user enters to date {string}")
    public void theUserEntersToDate(String date) {
        applyLeave.enterToDate(date);
    }

    @When("the user enters leave comment {string}")
    public void theUserEntersLeaveComment(String comment) {
        applyLeave.enterComments(comment);
    }

    @When("the user clicks Apply button")
    public void theUserClicksApplyButton() {
        applyLeave.clickApply();
    }

    @Then("the leave toast notification should be displayed")
    public void theLeaveToastShouldBeDisplayed() {
        Assert.assertTrue(applyLeave.isToastDisplayed(),
                "Toast notification should be displayed after applying leave");
    }

    @When("the user navigates to My Leave list")
    public void theUserNavigatesToMyLeaveList() {
        applyLeave.goToMyLeaveList();
    }

    @Then("the leave list table should be displayed")
    public void theLeaveListTableShouldBeDisplayed() {
        Assert.assertTrue(applyLeave.isLeaveListTableDisplayed(),
                "Leave list table should be displayed");
    }
}
