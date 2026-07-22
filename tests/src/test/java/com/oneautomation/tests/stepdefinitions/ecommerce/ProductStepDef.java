package com.oneautomation.tests.stepdefinitions.ecommerce;

import com.oneautomation.tests.common.EndpointResolver;
import com.oneautomation.tests.pageobjects.ecommerce.ProductPagePO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductStepDef {

    private final ProductPagePO productPage = new ProductPagePO();

    @Given("the user is on the product page")
    public void theUserIsOnTheProductPage() {
        productPage.genericMethod.fe().goToPageURL(EndpointResolver.getWebURL("/products"));
    }

    @When("the user searches for product {string}")
    public void theUserSearchesForProduct(String productName) {
        productPage.searchProduct(productName);
    }

    @When("the user selects the product {string}")
    public void theUserSelectsProduct(String productName) {
        productPage.selectProduct(productName);
    }

    @When("the user adds the product to cart")
    public void theUserAddsProductToCart() {
        productPage.clickAddToCart();
    }

    @Then("the product title should be {string}")
    public void theProductTitleShouldBe(String expectedTitle) {
        Assert.assertEquals(productPage.getProductTitle(), expectedTitle);
    }

    @Then("the cart count should be {string}")
    public void theCartCountShouldBe(String expectedCount) {
        Assert.assertEquals(productPage.getCartCount(), expectedCount);
    }

    @Then("the product list should have {int} items")
    public void theProductListShouldHaveItems(int expectedCount) {
        Assert.assertEquals(productPage.getProductCount(), expectedCount);
    }
}
