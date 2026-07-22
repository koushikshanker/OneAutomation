package com.oneautomation.tests.stepdefinitions.admin;

import com.oneautomation.tests.pageobjects.admin.UserManagementPO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AdminStepDef {

    private final UserManagementPO userManagement = new UserManagementPO();

    @Then("the System Users page should be displayed")
    public void theSystemUsersPageShouldBeDisplayed() {
        Assert.assertTrue(userManagement.isSystemUsersPageDisplayed(),
                "System Users page should be displayed");
    }

    @When("the user searches by username {string}")
    public void theUserSearchesByUsername(String username) {
        userManagement.searchByUsername(username);
    }

    @When("the user filters by user role {string}")
    public void theUserFiltersByUserRole(String role) {
        userManagement.filterByUserRole(role);
    }

    @When("the user filters by status {string}")
    public void theUserFiltersByStatus(String status) {
        userManagement.filterByStatus(status);
    }

    @When("the user clicks Search on admin page")
    public void theUserClicksSearchOnAdminPage() {
        userManagement.clickSearch();
    }

    @When("the user clicks Reset on admin page")
    public void theUserClicksResetOnAdminPage() {
        userManagement.clickReset();
    }

    @When("the user clicks Add on admin page")
    public void theUserClicksAddOnAdminPage() {
        userManagement.clickAdd();
    }

    @Then("the admin table should have records")
    public void theAdminTableShouldHaveRecords() {
        int count = userManagement.getTableRowCount();
        Assert.assertTrue(count > 0,
                "Admin table should have at least one record, found: " + count);
    }

    @Then("the admin records count should be displayed")
    public void theAdminRecordsCountShouldBeDisplayed() {
        String text = userManagement.getRecordsCountText();
        Assert.assertNotNull(text, "Records count text should be displayed");
        Assert.assertTrue(text.contains("Record"),
                "Records text should contain 'Record', was: " + text);
    }

    @Then("the admin toast should be displayed")
    public void theAdminToastShouldBeDisplayed() {
        Assert.assertTrue(userManagement.isToastDisplayed(),
                "Toast notification should be displayed");
    }
}
