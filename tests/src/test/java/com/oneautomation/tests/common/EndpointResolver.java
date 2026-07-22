package com.oneautomation.tests.common;

import com.oneautomation.framework.config.ConfigMap;
import com.oneautomation.framework.config.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndpointResolver {

    private static final Logger log = LoggerFactory.getLogger(EndpointResolver.class);

    public static String getURL(String path, String type) {
        String baseUrl;
        if ("api".equalsIgnoreCase(type)) {
            baseUrl = PropertyUtils.get(ConfigMap.API_BASE_URL);
        } else {
            baseUrl = PropertyUtils.get(ConfigMap.BASE_URL);
        }

        if (baseUrl == null) {
            log.error("Base URL not configured for type: {}", type);
            return path;
        }

        if (baseUrl.endsWith("/") && path.startsWith("/")) {
            return baseUrl + path.substring(1);
        }

        if (!baseUrl.endsWith("/") && !path.startsWith("/")) {
            return baseUrl + "/" + path;
        }

        return baseUrl + path;
    }

    public static String getWebURL(String path) {
        return getURL(path, "web");
    }

    public static String getApiURL(String path) {
        return getURL(path, "api");
    }
}
