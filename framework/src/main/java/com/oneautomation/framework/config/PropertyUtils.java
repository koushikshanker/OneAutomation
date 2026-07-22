package com.oneautomation.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class PropertyUtils {

    private static final Logger log = LoggerFactory.getLogger(PropertyUtils.class);
    private static final Properties properties = new Properties();

    private PropertyUtils() {
    }

    public static void init(String env) {
        String fileName = "config/" + env + ".properties";
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                log.warn("Config file not found on classpath: {}", fileName);
                return;
            }
            properties.load(is);
            log.info("Loaded configuration from: {}", fileName);
        } catch (IOException e) {
            log.error("Failed to load configuration from: {}", fileName, e);
        }
    }

    public static String get(ConfigMap key) {
        String envKey = key.name().toLowerCase().replace("_", ".");
        String systemProp = System.getProperty(envKey);
        if (systemProp != null && !systemProp.isEmpty()) {
            return systemProp;
        }
        return properties.getProperty(envKey);
    }

    public static String get(String key) {
        String systemProp = System.getProperty(key);
        if (systemProp != null && !systemProp.isEmpty()) {
            return systemProp;
        }
        return properties.getProperty(key);
    }

    public static int getInt(ConfigMap key, int defaultVal) {
        String value = get(key);
        if (value == null || value.isEmpty()) {
            return defaultVal;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            log.warn("Invalid integer value for key {}: {}", key, value);
            return defaultVal;
        }
    }

    public static boolean getBool(ConfigMap key, boolean defaultVal) {
        String value = get(key);
        if (value == null || value.isEmpty()) {
            return defaultVal;
        }
        return Boolean.parseBoolean(value.trim());
    }

    public static boolean keyPresent(ConfigMap key) {
        return Objects.nonNull(get(key));
    }
}
