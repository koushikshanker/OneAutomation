package com.oneautomation.framework.locator;

import org.openqa.selenium.By;

public abstract class Locator {

    protected By by;
    protected String description;

    protected Locator(By by, String description) {
        this.by = by;
        this.description = description;
    }

    public By toBy() {
        return by;
    }

    public String getDescription() {
        return description;
    }

    public abstract Locator parameterized(String... args);

    @Override
    public String toString() {
        if (description != null && !description.isEmpty()) {
            return by.toString() + " (" + description + ")";
        }
        return by.toString();
    }

    // --- Static factory methods ---

    public static Locator id(String id) {
        return new ById(id, "");
    }

    public static Locator id(String id, String description) {
        return new ById(id, description);
    }

    public static Locator name(String name) {
        return new ByName(name, "");
    }

    public static Locator name(String name, String description) {
        return new ByName(name, description);
    }

    public static Locator xpath(String xpath) {
        return new ByXPath(xpath, "");
    }

    public static Locator xpath(String xpath, String description) {
        return new ByXPath(xpath, description);
    }

    public static Locator cssSelector(String css) {
        return new ByCssSelector(css, "");
    }

    public static Locator cssSelector(String css, String description) {
        return new ByCssSelector(css, description);
    }

    public static Locator className(String className) {
        return new ByClassName(className, "");
    }

    public static Locator className(String className, String description) {
        return new ByClassName(className, description);
    }

    public static Locator tagName(String tagName) {
        return new ByTagName(tagName, "");
    }

    public static Locator tagName(String tagName, String description) {
        return new ByTagName(tagName, description);
    }

    public static Locator linkText(String linkText) {
        return new ByLinkText(linkText, "");
    }

    public static Locator linkText(String linkText, String description) {
        return new ByLinkText(linkText, description);
    }

    public static Locator partialLinkText(String partialLinkText) {
        return new ByPartialLinkText(partialLinkText, "");
    }

    public static Locator partialLinkText(String partialLinkText, String description) {
        return new ByPartialLinkText(partialLinkText, description);
    }

    // --- Helper for parameterized replacement ---

    protected static String replaceParams(String locator, String... args) {
        String result = locator;
        for (int i = 0; i < args.length; i++) {
            result = result.replace("${" + i + "}", args[i]);
        }
        return result;
    }

    // --- Inner classes ---

    private static class ById extends Locator {
        private final String rawId;

        ById(String id, String description) {
            super(By.id(id), description);
            this.rawId = id;
        }

        @Override
        public Locator parameterized(String... args) {
            return new ById(replaceParams(rawId, args), description);
        }
    }

    private static class ByName extends Locator {
        private final String rawName;

        ByName(String name, String description) {
            super(By.name(name), description);
            this.rawName = name;
        }

        @Override
        public Locator parameterized(String... args) {
            return new ByName(replaceParams(rawName, args), description);
        }
    }

    private static class ByXPath extends Locator {
        private final String rawXpath;

        ByXPath(String xpath, String description) {
            super(By.xpath(xpath), description);
            this.rawXpath = xpath;
        }

        @Override
        public Locator parameterized(String... args) {
            return new ByXPath(replaceParams(rawXpath, args), description);
        }
    }

    private static class ByCssSelector extends Locator {
        private final String rawCss;

        ByCssSelector(String css, String description) {
            super(By.cssSelector(css), description);
            this.rawCss = css;
        }

        @Override
        public Locator parameterized(String... args) {
            return new ByCssSelector(replaceParams(rawCss, args), description);
        }
    }

    private static class ByClassName extends Locator {
        private final String rawClassName;

        ByClassName(String className, String description) {
            super(By.className(className), description);
            this.rawClassName = className;
        }

        @Override
        public Locator parameterized(String... args) {
            return new ByClassName(replaceParams(rawClassName, args), description);
        }
    }

    private static class ByTagName extends Locator {
        private final String rawTagName;

        ByTagName(String tagName, String description) {
            super(By.tagName(tagName), description);
            this.rawTagName = tagName;
        }

        @Override
        public Locator parameterized(String... args) {
            return new ByTagName(replaceParams(rawTagName, args), description);
        }
    }

    private static class ByLinkText extends Locator {
        private final String rawLinkText;

        ByLinkText(String linkText, String description) {
            super(By.linkText(linkText), description);
            this.rawLinkText = linkText;
        }

        @Override
        public Locator parameterized(String... args) {
            return new ByLinkText(replaceParams(rawLinkText, args), description);
        }
    }

    private static class ByPartialLinkText extends Locator {
        private final String rawPartialLinkText;

        ByPartialLinkText(String partialLinkText, String description) {
            super(By.partialLinkText(partialLinkText), description);
            this.rawPartialLinkText = partialLinkText;
        }

        @Override
        public Locator parameterized(String... args) {
            return new ByPartialLinkText(replaceParams(rawPartialLinkText, args), description);
        }
    }
}
