package com.oneautomation.framework.driver;

import com.oneautomation.framework.config.ConfigMap;
import com.oneautomation.framework.config.GlobalVars;
import com.oneautomation.framework.config.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DriverInitiator {

    private static final Logger log = LoggerFactory.getLogger(DriverInitiator.class);

    public static void initDriver(String browserType) {
        BrowserType browser = BrowserType.fromString(browserType);
        RemoteWebDriver driver;

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeBrowserOption());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getFirefoxBrowserOption());
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(getEdgeBrowserOption());
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }

        int implicitWait = PropertyUtils.getInt(ConfigMap.IMPLICIT_WAIT, 10);
        int pageLoadTimeout = PropertyUtils.getInt(ConfigMap.PAGE_LOAD_TIMEOUT, 30);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().window().maximize();

        DriverManager.getInstance().setDriver(driver);
        log.info("Initialized {} browser", browser);
    }

    public static ChromeOptions getChromeBrowserOption() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-certificate-errors");

        boolean headless = PropertyUtils.getBool(ConfigMap.HEADLESS, false);
        if (headless) {
            options.addArguments("--headless=new");
        }

        if (GlobalVars.isMac()) {
            String chromePath = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
            if (new java.io.File(chromePath).exists()) {
                options.setBinary(chromePath);
            }
        }

        return options;
    }

    public static FirefoxOptions getFirefoxBrowserOption() {
        FirefoxOptions options = new FirefoxOptions();
        boolean headless = PropertyUtils.getBool(ConfigMap.HEADLESS, false);
        if (headless) {
            options.addArguments("--headless");
        }
        return options;
    }

    public static EdgeOptions getEdgeBrowserOption() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        boolean headless = PropertyUtils.getBool(ConfigMap.HEADLESS, false);
        if (headless) {
            options.addArguments("--headless=new");
        }

        return options;
    }
}
