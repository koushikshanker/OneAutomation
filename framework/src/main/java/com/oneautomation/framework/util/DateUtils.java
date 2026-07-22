package com.oneautomation.framework.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    private DateUtils() {
    }

    public static String getCurrentDate(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getDatePlusDays(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getDateMinusDays(int days, String pattern) {
        return LocalDate.now().minusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate(String dateStr, String inputPattern, String outputPattern) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(inputPattern));
        return date.format(DateTimeFormatter.ofPattern(outputPattern));
    }
}
