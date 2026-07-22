package com.oneautomation.tests.pageobjects.ecommerce;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class CheckoutPO extends WebBase {

    private final Locator cartSummary = Locator.cssSelector(".cart-summary", "Cart summary section");
    private final Locator totalPrice = Locator.cssSelector(".total-price", "Total price");
    private final Locator checkoutButton = Locator.cssSelector(".checkout-btn", "Checkout button");
    private final Locator orderConfirmation = Locator.cssSelector(".order-confirmation", "Order confirmation message");
    private final Locator removeItemButton = Locator.cssSelector(".remove-item", "Remove item button");
    private final Locator quantityInput = Locator.cssSelector(".quantity-input", "Quantity input");
    private final Locator updateCartButton = Locator.cssSelector(".update-cart", "Update cart button");

    public boolean isCartSummaryDisplayed() {
        return genericMethod.isDisplayed(cartSummary);
    }

    public String getTotalPrice() {
        wait.waitForVisibilityOfElement(totalPrice);
        return genericMethod.getText(totalPrice);
    }

    public void clickCheckout() {
        wait.waitForElementToBeClickable(checkoutButton);
        genericMethod.click(checkoutButton);
    }

    public String getOrderConfirmation() {
        wait.waitForVisibilityOfElement(orderConfirmation);
        return genericMethod.getText(orderConfirmation);
    }

    public void removeItem() {
        wait.waitForElementToBeClickable(removeItemButton);
        genericMethod.click(removeItemButton);
    }

    public void updateQuantity(String quantity) {
        genericMethod.clearText(quantityInput);
        genericMethod.sendKeys(quantityInput, quantity);
        genericMethod.click(updateCartButton);
    }
}
