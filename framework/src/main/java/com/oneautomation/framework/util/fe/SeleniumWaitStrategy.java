package com.oneautomation.framework.util.fe;

import com.oneautomation.framework.config.ConfigMap;
import com.oneautomation.framework.config.PropertyUtils;
import com.oneautomation.framework.driver.DriverManager;
import com.oneautomation.framework.locator.Locator;
import com.oneautomation.framework.util.wrapper.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class SeleniumWaitStrategy implements WaitStrategy {

    private static final Logger log = LoggerFactory.getLogger(SeleniumWaitStrategy.class);

    private RemoteWebDriver getDriver() {
        return DriverManager.getInstance().getDriver();
    }

    private int getDefaultTimeout() {
        return PropertyUtils.getInt(ConfigMap.EXPLICIT_WAIT, 15);
    }

    private WebDriverWait createWait(int timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
    }

    @Override
    public WebElement waitForVisibilityOfElement(Locator locator) {
        return waitForVisibilityOfElement(locator, getDefaultTimeout());
    }

    @Override
    public WebElement waitForVisibilityOfElement(Locator locator, int timeoutInSeconds) {
        log.debug("Waiting for visibility of element: {}", locator);
        return createWait(timeoutInSeconds).until(
                ExpectedConditions.visibilityOfElementLocated(locator.toBy()));
    }

    @Override
    public WebElement waitForElementToBeClickable(Locator locator) {
        return waitForElementToBeClickable(locator, getDefaultTimeout());
    }

    @Override
    public WebElement waitForElementToBeClickable(Locator locator, int timeoutInSeconds) {
        log.debug("Waiting for element to be clickable: {}", locator);
        return createWait(timeoutInSeconds).until(
                ExpectedConditions.elementToBeClickable(locator.toBy()));
    }

    @Override
    public boolean waitForInvisibilityOfElement(Locator locator) {
        return waitForInvisibilityOfElement(locator, getDefaultTimeout());
    }

    @Override
    public boolean waitForInvisibilityOfElement(Locator locator, int timeoutInSeconds) {
        log.debug("Waiting for invisibility of element: {}", locator);
        return createWait(timeoutInSeconds).until(
                ExpectedConditions.invisibilityOfElementLocated(locator.toBy()));
    }

    @Override
    public WebElement getWebElement(Locator locator) {
        return waitForVisibilityOfElement(locator);
    }

    @Override
    public List<WebElement> waitAndFindElements(Locator locator) {
        log.debug("Waiting and finding elements: {}", locator);
        createWait(getDefaultTimeout()).until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(locator.toBy()));
        return getDriver().findElements(locator.toBy());
    }

    @Override
    public void hardWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Hard wait interrupted");
        }
    }

    @Override
    public By convertToBy(Locator locator) {
        return locator.toBy();
    }

    @Override
    public String getText(Locator locator) {
        return waitForVisibilityOfElement(locator).getText();
    }

    @Override
    public void click(Locator locator) {
        log.debug("Wait and click element: {}", locator);
        waitForElementToBeClickable(locator).click();
    }
}
