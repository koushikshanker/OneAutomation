package com.oneautomation.framework.config;

public final class GlobalVars {

    private GlobalVars() {
    }

    public static String getOsPlatform() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    public static String getScreenshotDir() {
        String dir = PropertyUtils.get(ConfigMap.SCREENSHOT_DIR);
        return dir != null ? dir : "screenshots";
    }

    public static String getReportDir() {
        String dir = PropertyUtils.get(ConfigMap.REPORT_DIR);
        return dir != null ? dir : "reports";
    }

    public static boolean isWindows() {
        return getOsPlatform().contains("win");
    }

    public static boolean isMac() {
        return getOsPlatform().contains("mac");
    }

    public static boolean isLinux() {
        return getOsPlatform().contains("nux") || getOsPlatform().contains("nix");
    }
}
