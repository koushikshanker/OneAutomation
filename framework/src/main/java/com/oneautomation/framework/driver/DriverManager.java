package com.oneautomation.framework.driver;

import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    private static final DriverManager INSTANCE = new DriverManager();
    private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        return INSTANCE;
    }

    public RemoteWebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(RemoteWebDriver webDriver) {
        driver.set(webDriver);
    }

    public boolean hasDriver() {
        return driver.get() != null;
    }

    public void quitDriver() {
        RemoteWebDriver d = driver.get();
        if (d != null) {
            d.quit();
            driver.remove();
        }
    }
}
