package com.oneautomation.tests.pageobjects.buzz;

import com.oneautomation.framework.base.fe.WebBase;
import com.oneautomation.framework.locator.Locator;

public class BuzzPO extends WebBase {

    private final Locator buzzPostInput = Locator.cssSelector(
            ".oxd-buzz-post-input", "Buzz post input area");
    private final Locator buzzTextarea = Locator.cssSelector(
            "textarea.oxd-buzz-post-input", "Buzz textarea");
    private final Locator shareButton = Locator.xpath(
            "//button[normalize-space()='Post']", "Post button");
    private final Locator buzzFeedPosts = Locator.cssSelector(
            ".orangehrm-buzz-post", "Buzz feed posts");
    private final Locator buzzHeader = Locator.xpath(
            "//h6[normalize-space(.)='Buzz']", "Buzz header");
    private final Locator buzzNewsfeed = Locator.cssSelector(
            ".orangehrm-buzz-newsfeed", "Buzz newsfeed container");

    public boolean isBuzzPageDisplayed() {
        return genericMethod.isDisplayed(buzzHeader) || genericMethod.isDisplayed(buzzNewsfeed);
    }

    public boolean isBuzzPostInputVisible() {
        return genericMethod.isDisplayed(buzzPostInput);
    }

    public int getBuzzPostCount() {
        wait.hardWait(2);
        return genericMethod.getElements(buzzFeedPosts).size();
    }

    public boolean hasBuzzPosts() {
        return getBuzzPostCount() > 0;
    }
}
