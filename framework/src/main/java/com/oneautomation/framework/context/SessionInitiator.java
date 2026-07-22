package com.oneautomation.framework.context;

import com.oneautomation.framework.config.ConfigMap;
import com.oneautomation.framework.config.PropertyUtils;
import com.oneautomation.framework.driver.DriverInitiator;
import com.oneautomation.framework.driver.DriverManager;
import com.oneautomation.framework.util.ScreenshotUtils;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionInitiator {

    private static final Logger log = LoggerFactory.getLogger(SessionInitiator.class);
    private static final SessionInitiator INSTANCE = new SessionInitiator();

    private String browser;
    private String environment;
    private String automationType;

    private SessionInitiator() {
    }

    public static SessionInitiator getInstance() {
        return INSTANCE;
    }

    public void initializeSession() {
        environment = PropertyUtils.get(ConfigMap.ENV);
        if (environment == null || environment.isEmpty()) {
            environment = System.getProperty("env", "dev");
        }

        PropertyUtils.init(environment);

        browser = PropertyUtils.get(ConfigMap.BROWSER);
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        automationType = PropertyUtils.get(ConfigMap.AUTOMATION_TYPE);
        if (automationType == null || automationType.isEmpty()) {
            automationType = "DWEB";
        }

        log.info("Session initialized - env: {}, browser: {}, type: {}", environment, browser, automationType);
    }

    public void initializeDriver() {
        DriverInitiator.initDriver(browser);
    }

    public void closeDriverSession() {
        DriverManager.getInstance().quitDriver();
        CacheMechanism.getInstance().clear();
        log.info("Driver session closed and cache cleared");
    }

    public void saveScreenshot(Scenario scenario) {
        if (scenario.isFailed() && DriverManager.getInstance().hasDriver()) {
            byte[] screenshot = ScreenshotUtils.takeScreenshotAsBytes();
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", scenario.getName());
                log.info("Screenshot attached for failed scenario: {}", scenario.getName());
            }
        }
    }

    public String getBrowser() {
        return browser;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getAutomationType() {
        return automationType;
    }
}
