package com.oneautomation.framework.util;

import com.oneautomation.framework.config.GlobalVars;
import com.oneautomation.framework.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotUtils.class);

    private ScreenshotUtils() {
    }

    public static byte[] takeScreenshotAsBytes() {
        RemoteWebDriver driver = DriverManager.getInstance().getDriver();
        if (driver == null) {
            return null;
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static String takeScreenshotAsFile(String scenarioName) {
        RemoteWebDriver driver = DriverManager.getInstance().getDriver();
        if (driver == null) {
            return null;
        }

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            Path destPath = Paths.get(GlobalVars.getScreenshotDir(), fileName);
            Files.createDirectories(destPath.getParent());
            Files.copy(srcFile.toPath(), destPath);
            log.info("Screenshot saved: {}", destPath);
            return destPath.toString();
        } catch (IOException e) {
            log.error("Failed to save screenshot", e);
            return null;
        }
    }
}
