package com.oneautomation.framework.util.fe;

import com.oneautomation.framework.driver.DriverManager;
import com.oneautomation.framework.locator.Locator;
import com.oneautomation.framework.util.wrapper.JavaScriptSupport;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SeleniumJavaScriptSupport implements JavaScriptSupport {

    private static final Logger log = LoggerFactory.getLogger(SeleniumJavaScriptSupport.class);

    private RemoteWebDriver getDriver() {
        return DriverManager.getInstance().getDriver();
    }

    private JavascriptExecutor getJS() {
        return (JavascriptExecutor) getDriver();
    }

    @Override
    public void click(Locator locator) {
        log.debug("JS click on element: {}", locator);
        WebElement element = getDriver().findElement(locator.toBy());
        getJS().executeScript("arguments[0].click();", element);
    }

    @Override
    public void click(WebElement element) {
        log.debug("JS click on WebElement");
        getJS().executeScript("arguments[0].click();", element);
    }

    @Override
    public String getText(Locator locator) {
        WebElement element = getDriver().findElement(locator.toBy());
        return (String) getJS().executeScript("return arguments[0].textContent;", element);
    }

    @Override
    public void sendKeys(Locator locator, String text) {
        WebElement element = getDriver().findElement(locator.toBy());
        getJS().executeScript("arguments[0].value=arguments[1];", element, text);
    }

    @Override
    public void scrollToElement(Locator locator) {
        log.debug("Scrolling to element: {}", locator);
        WebElement element = getDriver().findElement(locator.toBy());
        getJS().executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    @Override
    public void scrollToBottom() {
        getJS().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    @Override
    public void scrollUp() {
        getJS().executeScript("window.scrollTo(0, 0);");
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return getJS().executeScript(script, args);
    }

    @Override
    public boolean isElementInViewport(Locator locator) {
        WebElement element = getDriver().findElement(locator.toBy());
        return (Boolean) getJS().executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.left >= 0 && " +
                        "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                        "rect.right <= (window.innerWidth || document.documentElement.clientWidth));",
                element);
    }

    @Override
    public void waitForPageLoad() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(30)).until(
                driver -> getJS().executeScript("return document.readyState").equals("complete"));
    }
}
