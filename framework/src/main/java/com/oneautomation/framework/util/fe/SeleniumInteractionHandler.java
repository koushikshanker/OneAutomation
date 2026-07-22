package com.oneautomation.framework.util.fe;

import com.oneautomation.framework.driver.DriverManager;
import com.oneautomation.framework.locator.Locator;
import com.oneautomation.framework.util.wrapper.InteractionHandler;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumInteractionHandler implements InteractionHandler {

    private static final Logger log = LoggerFactory.getLogger(SeleniumInteractionHandler.class);

    private RemoteWebDriver getDriver() {
        return DriverManager.getInstance().getDriver();
    }

    private Actions getActions() {
        return new Actions(getDriver());
    }

    private WebElement findElement(Locator locator) {
        return getDriver().findElement(locator.toBy());
    }

    @Override
    public void clickByAction(Locator locator) {
        log.debug("Action click on element: {}", locator);
        getActions().click(findElement(locator)).perform();
    }

    @Override
    public void doubleClick(Locator locator) {
        log.debug("Double click on element: {}", locator);
        getActions().doubleClick(findElement(locator)).perform();
    }

    @Override
    public void contextClickByAction(Locator locator) {
        log.debug("Context click on element: {}", locator);
        getActions().contextClick(findElement(locator)).perform();
    }

    @Override
    public void hoverByAction(Locator locator) {
        log.debug("Hovering over element: {}", locator);
        getActions().moveToElement(findElement(locator)).perform();
    }

    @Override
    public void sendKeysByAction(Locator locator, String text) {
        log.debug("Action sendKeys to element: {}", locator);
        getActions().sendKeys(findElement(locator), text).perform();
    }

    @Override
    public void moveByOffset(int xOffset, int yOffset) {
        log.debug("Moving by offset: ({}, {})", xOffset, yOffset);
        getActions().moveByOffset(xOffset, yOffset).perform();
    }
}
