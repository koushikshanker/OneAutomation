package com.oneautomation.tests.stepdefinitions.pim;

import com.oneautomation.tests.pageobjects.pim.EmployeeListPO;
import com.oneautomation.tests.pageobjects.pim.AddEmployeePO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class PimStepDef {

    private final EmployeeListPO employeeList = new EmployeeListPO();
    private final AddEmployeePO addEmployee = new AddEmployeePO();
    private String savedEmployeeId;

    @When("the user clicks Add Employee button")
    public void theUserClicksAddEmployeeButton() {
        employeeList.clickAdd();
    }

    @When("the user adds employee with first name {string} and last name {string}")
    public void theUserAddsEmployee(String firstName, String lastName) {
        addEmployee.addEmployee(firstName, lastName);
    }

    @When("the user adds employee with name {string} {string} {string}")
    public void theUserAddsEmployeeWithMiddleName(String firstName, String middleName, String lastName) {
        addEmployee.addEmployeeWithMiddleName(firstName, middleName, lastName);
    }

    @When("the user saves the employee ID")
    public void theUserSavesTheEmployeeId() {
        savedEmployeeId = addEmployee.getEmployeeId();
        Assert.assertNotNull(savedEmployeeId, "Employee ID should be auto-generated");
    }

    @When("the user enters first name {string}")
    public void theUserEntersFirstName(String firstName) {
        addEmployee.enterFirstName(firstName);
    }

    @When("the user enters last name {string}")
    public void theUserEntersLastName(String lastName) {
        addEmployee.enterLastName(lastName);
    }

    @When("the user clicks Save button")
    public void theUserClicksSaveButton() {
        addEmployee.clickSave();
    }

    @Then("the success toast should be displayed")
    public void theSuccessToastShouldBeDisplayed() {
        Assert.assertTrue(addEmployee.isToastDisplayed(),
                "Success toast notification should be displayed");
    }

    @Then("the toast message should contain {string}")
    public void theToastMessageShouldContain(String expectedText) {
        String toastText = addEmployee.getToastText();
        Assert.assertTrue(toastText.contains(expectedText),
                "Toast should contain '" + expectedText + "' but was: " + toastText);
    }

    @When("the user searches employee by name {string}")
    public void theUserSearchesEmployeeByName(String name) {
        employeeList.searchByEmployeeName(name);
    }

    @When("the user searches employee by ID {string}")
    public void theUserSearchesEmployeeById(String id) {
        employeeList.searchByEmployeeId(id);
    }

    @When("the user clicks Reset on employee list")
    public void theUserClicksResetOnEmployeeList() {
        employeeList.clickReset();
    }

    @Then("the employee table should have records")
    public void theEmployeeTableShouldHaveRecords() {
        int rowCount = employeeList.getTableRowCount();
        Assert.assertTrue(rowCount > 0,
                "Employee table should have at least one record, found: " + rowCount);
    }

    @Then("the records count text should be displayed")
    public void theRecordsCountTextShouldBeDisplayed() {
        String recordsText = employeeList.getRecordsCountText();
        Assert.assertNotNull(recordsText, "Records count text should be displayed");
        Assert.assertTrue(recordsText.contains("Record"),
                "Records text should mention 'Record', was: " + recordsText);
    }

    @When("the user clicks edit on first employee")
    public void theUserClicksEditOnFirstEmployee() {
        employeeList.clickFirstEditIcon();
    }

    @Then("the employee list toast should be displayed")
    public void theEmployeeListToastShouldBeDisplayed() {
        Assert.assertTrue(employeeList.isToastDisplayed(),
                "Toast notification should be displayed");
    }

    @Then("no records found should be displayed")
    public void noRecordsFoundShouldBeDisplayed() {
        Assert.assertTrue(employeeList.isNoRecordsFound(),
                "No Records Found message should be displayed");
    }

    @When("the user enables create login details")
    public void theUserEnablesCreateLoginDetails() {
        addEmployee.toggleCreateLogin();
    }

    @When("the user enters login credentials username {string} and password {string}")
    public void theUserEntersLoginCredentials(String username, String password) {
        addEmployee.enterLoginCredentials(username, password);
    }
}
