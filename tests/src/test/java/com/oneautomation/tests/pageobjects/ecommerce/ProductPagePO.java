package com.oneautomation.tests.pageobjects.ecommerce;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class ProductPagePO extends WebBase {

    private final Locator productTitle = Locator.cssSelector(".product-title", "Product title");
    private final Locator productPrice = Locator.cssSelector(".product-price", "Product price");
    private final Locator addToCartButton = Locator.cssSelector(".add-to-cart", "Add to cart button");
    private final Locator cartCount = Locator.cssSelector(".cart-count", "Cart item count");
    private final Locator productList = Locator.cssSelector(".product-item", "Product list items");
    private final Locator searchInput = Locator.id("search", "Product search input");
    private final Locator searchButton = Locator.cssSelector(".search-btn", "Search button");
    private final Locator productByName = Locator.xpath("//div[contains(@class,'product-item')]//h3[text()='${0}']", "Product by name");

    public String getProductTitle() {
        wait.waitForVisibilityOfElement(productTitle);
        return genericMethod.getText(productTitle);
    }

    public String getProductPrice() {
        wait.waitForVisibilityOfElement(productPrice);
        return genericMethod.getText(productPrice);
    }

    public void clickAddToCart() {
        wait.waitForElementToBeClickable(addToCartButton);
        genericMethod.click(addToCartButton);
    }

    public String getCartCount() {
        wait.waitForVisibilityOfElement(cartCount);
        return genericMethod.getText(cartCount);
    }

    public int getProductCount() {
        return genericMethod.getElements(productList).size();
    }

    public void searchProduct(String productName) {
        genericMethod.sendKeys(searchInput, productName);
        genericMethod.click(searchButton);
    }

    public void selectProduct(String productName) {
        Locator product = genericMethod.parameterizedLocator(productByName, productName);
        wait.waitForElementToBeClickable(product);
        genericMethod.click(product);
    }
}
