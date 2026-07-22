package com.oneautomation.framework.util.wrapper;

import com.oneautomation.framework.locator.Locator;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface ElementActions {

    void click(Locator locator);

    void sendKeys(Locator locator, String text);

    void clearText(Locator locator);

    String getText(Locator locator);

    boolean isDisplayed(Locator locator);

    boolean isClickable(Locator locator);

    boolean isEnabled(Locator locator);

    boolean isSelected(Locator locator);

    WebElement getElement(Locator locator);

    List<WebElement> getElements(Locator locator);

    void selectByVisibleText(Locator locator, String text);

    void selectByValue(Locator locator, String value);

    String getAttribute(Locator locator, String attribute);

    Locator parameterizedLocator(Locator locator, String... args);

    SeleniumGenericUtility fe();

    interface SeleniumGenericUtility {

        void goToPageURL(String url);

        String getCurrentURL();

        String getPageTitle();

        String getWindowHandle();

        String getParentWindowHandle();

        void switchToWindow(int index);

        void switchToChildWindow();

        void switchToParentWindow();

        void switchToParentWindow(String parentHandle);

        void switchToFrame(Locator locator);

        void switchToDefaultContent();

        String getCookieByName(String name);

        void deleteAllCookies();

        void navigateBack();

        void navigateForward();

        void refreshPage();
    }
}
