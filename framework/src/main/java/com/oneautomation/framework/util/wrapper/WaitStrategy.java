package com.oneautomation.framework.util.wrapper;

import com.oneautomation.framework.locator.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface WaitStrategy {

    WebElement waitForVisibilityOfElement(Locator locator);

    WebElement waitForVisibilityOfElement(Locator locator, int timeoutInSeconds);

    WebElement waitForElementToBeClickable(Locator locator);

    WebElement waitForElementToBeClickable(Locator locator, int timeoutInSeconds);

    boolean waitForInvisibilityOfElement(Locator locator);

    boolean waitForInvisibilityOfElement(Locator locator, int timeoutInSeconds);

    WebElement getWebElement(Locator locator);

    List<WebElement> waitAndFindElements(Locator locator);

    void hardWait(int seconds);

    By convertToBy(Locator locator);

    String getText(Locator locator);

    void click(Locator locator);
}
