package com.oneautomation.framework.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TestNGLogUtility {

    private static final Logger log = LoggerFactory.getLogger(TestNGLogUtility.class);

    private TestNGLogUtility() {
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void info(String message, Object... args) {
        log.info(message, args);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void error(String message, Object... args) {
        log.error(message, args);
    }

    public static void error(String message, Throwable throwable) {
        log.error(message, throwable);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void warn(String message, Object... args) {
        log.warn(message, args);
    }

    public static void debug(String message) {
        log.debug(message);
    }

    public static void debug(String message, Object... args) {
        log.debug(message, args);
    }
}
