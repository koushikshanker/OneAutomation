package com.oneautomation.framework.util.fe;

import com.oneautomation.framework.driver.DriverManager;
import com.oneautomation.framework.locator.Locator;
import com.oneautomation.framework.util.wrapper.ElementActions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SeleniumElementActions implements ElementActions {

    private static final Logger log = LoggerFactory.getLogger(SeleniumElementActions.class);
    private final SeleniumGenericUtilityImpl seleniumUtility = new SeleniumGenericUtilityImpl();

    private RemoteWebDriver getDriver() {
        return DriverManager.getInstance().getDriver();
    }

    @Override
    public void click(Locator locator) {
        log.debug("Clicking element: {}", locator);
        getDriver().findElement(locator.toBy()).click();
    }

    @Override
    public void sendKeys(Locator locator, String text) {
        log.debug("Sending keys to element: {}", locator);
        WebElement element = getDriver().findElement(locator.toBy());
        element.clear();
        element.sendKeys(text);
    }

    @Override
    public void clearText(Locator locator) {
        log.debug("Clearing text from element: {}", locator);
        getDriver().findElement(locator.toBy()).clear();
    }

    @Override
    public String getText(Locator locator) {
        log.debug("Getting text from element: {}", locator);
        return getDriver().findElement(locator.toBy()).getText();
    }

    @Override
    public boolean isDisplayed(Locator locator) {
        try {
            return getDriver().findElement(locator.toBy()).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean isClickable(Locator locator) {
        try {
            WebElement element = getDriver().findElement(locator.toBy());
            return element.isDisplayed() && element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean isEnabled(Locator locator) {
        try {
            return getDriver().findElement(locator.toBy()).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean isSelected(Locator locator) {
        try {
            return getDriver().findElement(locator.toBy()).isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public WebElement getElement(Locator locator) {
        return getDriver().findElement(locator.toBy());
    }

    @Override
    public List<WebElement> getElements(Locator locator) {
        return getDriver().findElements(locator.toBy());
    }

    @Override
    public void selectByVisibleText(Locator locator, String text) {
        log.debug("Selecting '{}' from dropdown: {}", text, locator);
        Select select = new Select(getDriver().findElement(locator.toBy()));
        select.selectByVisibleText(text);
    }

    @Override
    public void selectByValue(Locator locator, String value) {
        log.debug("Selecting value '{}' from dropdown: {}", value, locator);
        Select select = new Select(getDriver().findElement(locator.toBy()));
        select.selectByValue(value);
    }

    @Override
    public String getAttribute(Locator locator, String attribute) {
        return getDriver().findElement(locator.toBy()).getAttribute(attribute);
    }

    @Override
    public Locator parameterizedLocator(Locator locator, String... args) {
        return locator.parameterized(args);
    }

    @Override
    public SeleniumGenericUtility fe() {
        return seleniumUtility;
    }

    private class SeleniumGenericUtilityImpl implements SeleniumGenericUtility {

        @Override
        public void goToPageURL(String url) {
            log.info("Navigating to URL: {}", url);
            getDriver().get(url);
        }

        @Override
        public String getCurrentURL() {
            return getDriver().getCurrentUrl();
        }

        @Override
        public String getPageTitle() {
            return getDriver().getTitle();
        }

        @Override
        public String getWindowHandle() {
            return getDriver().getWindowHandle();
        }

        @Override
        public String getParentWindowHandle() {
            return getDriver().getWindowHandle();
        }

        @Override
        public void switchToWindow(int index) {
            List<String> handles = new ArrayList<>(getDriver().getWindowHandles());
            if (index < handles.size()) {
                getDriver().switchTo().window(handles.get(index));
            }
        }

        @Override
        public void switchToChildWindow() {
            String parentHandle = getDriver().getWindowHandle();
            for (String handle : getDriver().getWindowHandles()) {
                if (!handle.equals(parentHandle)) {
                    getDriver().switchTo().window(handle);
                    break;
                }
            }
        }

        @Override
        public void switchToParentWindow() {
            List<String> handles = new ArrayList<>(getDriver().getWindowHandles());
            getDriver().switchTo().window(handles.get(0));
        }

        @Override
        public void switchToParentWindow(String parentHandle) {
            getDriver().switchTo().window(parentHandle);
        }

        @Override
        public void switchToFrame(Locator locator) {
            WebElement frame = getDriver().findElement(locator.toBy());
            getDriver().switchTo().frame(frame);
        }

        @Override
        public void switchToDefaultContent() {
            getDriver().switchTo().defaultContent();
        }

        @Override
        public String getCookieByName(String name) {
            var cookie = getDriver().manage().getCookieNamed(name);
            return cookie != null ? cookie.getValue() : null;
        }

        @Override
        public void deleteAllCookies() {
            getDriver().manage().deleteAllCookies();
        }

        @Override
        public void navigateBack() {
            getDriver().navigate().back();
        }

        @Override
        public void navigateForward() {
            getDriver().navigate().forward();
        }

        @Override
        public void refreshPage() {
            getDriver().navigate().refresh();
        }
    }
}
