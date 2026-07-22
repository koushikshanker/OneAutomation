package com.oneautomation.tests.stepdefinitions.ecommerce;

import com.oneautomation.tests.pageobjects.ecommerce.CheckoutPO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutStepDef {

    private final CheckoutPO checkoutPage = new CheckoutPO();

    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        checkoutPage.clickCheckout();
    }

    @When("the user updates item quantity to {string}")
    public void theUserUpdatesQuantity(String quantity) {
        checkoutPage.updateQuantity(quantity);
    }

    @When("the user removes an item from cart")
    public void theUserRemovesItem() {
        checkoutPage.removeItem();
    }

    @Then("the cart summary should be displayed")
    public void theCartSummaryShouldBeDisplayed() {
        Assert.assertTrue(checkoutPage.isCartSummaryDisplayed(), "Cart summary should be displayed");
    }

    @Then("the total price should be {string}")
    public void theTotalPriceShouldBe(String expectedPrice) {
        Assert.assertEquals(checkoutPage.getTotalPrice(), expectedPrice);
    }

    @Then("the order confirmation should be displayed")
    public void theOrderConfirmationShouldBeDisplayed() {
        String confirmation = checkoutPage.getOrderConfirmation();
        Assert.assertNotNull(confirmation, "Order confirmation should be displayed");
    }
}
