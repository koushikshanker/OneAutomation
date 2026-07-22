package com.oneautomation.tests.common;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedPageActions extends WebBase {

    private static final Logger log = LoggerFactory.getLogger(SharedPageActions.class);

    public void navigateToURL(String url) {
        log.info("Navigating to: {}", url);
        genericMethod.fe().goToPageURL(url);
        jsUtil.waitForPageLoad();
    }

    public void waitAndClick(Locator locator) {
        wait.waitForElementToBeClickable(locator);
        genericMethod.click(locator);
    }

    public void waitAndSendKeys(Locator locator, String text) {
        wait.waitForVisibilityOfElement(locator);
        genericMethod.sendKeys(locator, text);
    }

    public String waitAndGetText(Locator locator) {
        wait.waitForVisibilityOfElement(locator);
        return genericMethod.getText(locator);
    }

    public boolean isElementPresent(Locator locator) {
        return genericMethod.isDisplayed(locator);
    }
}
