package com.oneautomation.framework.util.wrapper;

import com.oneautomation.framework.locator.Locator;
import org.openqa.selenium.WebElement;

public interface JavaScriptSupport {

    void click(Locator locator);

    void click(WebElement element);

    String getText(Locator locator);

    void sendKeys(Locator locator, String text);

    void scrollToElement(Locator locator);

    void scrollToBottom();

    void scrollUp();

    Object executeScript(String script, Object... args);

    boolean isElementInViewport(Locator locator);

    void waitForPageLoad();
}
