package com.oneautomation.tests.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataProvider {

    private static final Logger log = LoggerFactory.getLogger(TestDataProvider.class);
    private static TestDataProvider instance;
    private final Properties testData = new Properties();

    private TestDataProvider() {
        loadTestData();
    }

    public static synchronized TestDataProvider getInstance() {
        if (instance == null) {
            instance = new TestDataProvider();
        }
        return instance;
    }

    private void loadTestData() {
        try (InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("testdata/Data.properties")) {
            if (is != null) {
                testData.load(is);
                log.info("Test data loaded successfully");
            } else {
                log.warn("Test data file not found: testdata/Data.properties");
            }
        } catch (IOException e) {
            log.error("Failed to load test data", e);
        }
    }

    public String checkData(String key) {
        String value = testData.getProperty(key);
        if (value == null) {
            log.warn("Test data key not found: {}", key);
        }
        return value;
    }
}
