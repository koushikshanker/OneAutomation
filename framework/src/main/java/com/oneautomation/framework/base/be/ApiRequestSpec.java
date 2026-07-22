package com.oneautomation.framework.base.be;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApiRequestSpec {

    private final String endpoint;
    private final String method;
    private final String basePath;
    private final Map<String, String> headers;
    private final Object body;
    private final Map<String, String> queryParams;
    private final Map<String, String> cookies;

    private ApiRequestSpec(RequestBuilder builder) {
        this.endpoint = builder.endpoint;
        this.method = builder.method;
        this.basePath = builder.basePath;
        this.headers = Collections.unmodifiableMap(new HashMap<>(builder.headers));
        this.body = builder.body;
        this.queryParams = Collections.unmodifiableMap(new HashMap<>(builder.queryParams));
        this.cookies = Collections.unmodifiableMap(new HashMap<>(builder.cookies));
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getMethod() {
        return method;
    }

    public String getBasePath() {
        return basePath;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Object getBody() {
        return body;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public static class RequestBuilder {

        private String endpoint = "";
        private String method = "GET";
        private String basePath = "";
        private final Map<String, String> headers = new HashMap<>();
        private Object body;
        private final Map<String, String> queryParams = new HashMap<>();
        private final Map<String, String> cookies = new HashMap<>();

        public RequestBuilder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public RequestBuilder method(String method) {
            this.method = method;
            return this;
        }

        public RequestBuilder basePath(String basePath) {
            this.basePath = basePath;
            return this;
        }

        public RequestBuilder headers(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public RequestBuilder header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public RequestBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public RequestBuilder queryParams(Map<String, String> queryParams) {
            this.queryParams.putAll(queryParams);
            return this;
        }

        public RequestBuilder queryParam(String key, String value) {
            this.queryParams.put(key, value);
            return this;
        }

        public RequestBuilder cookies(Map<String, String> cookies) {
            this.cookies.putAll(cookies);
            return this;
        }

        public RequestBuilder cookie(String key, String value) {
            this.cookies.put(key, value);
            return this;
        }

        public ApiRequestSpec build() {
            return new ApiRequestSpec(this);
        }
    }
}
