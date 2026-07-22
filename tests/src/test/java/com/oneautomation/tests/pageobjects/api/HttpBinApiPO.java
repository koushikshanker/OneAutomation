package com.oneautomation.tests.pageobjects.api;

import com.oneautomation.framework.base.be.ApiBase;
import com.oneautomation.framework.base.be.ApiRequestSpec;
import com.oneautomation.framework.base.be.ApiResponse;

public class HttpBinApiPO extends ApiBase {

    private static final String BASE = "https://httpbin.org";

    public ApiResponse getStatus(int code) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/status/" + code)
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse getHeaders() {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/headers")
                .header("X-Custom-Header", "TestValue123")
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse getResponseHeaders(String headerName, String headerValue) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/response-headers")
                .queryParam(headerName, headerValue)
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse basicAuth(String user, String password) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/basic-auth/" + user + "/" + password)
                .header("Authorization", "Basic " +
                        java.util.Base64.getEncoder().encodeToString((user + ":" + password).getBytes()))
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse basicAuthUnauthorized() {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/basic-auth/admin/secret")
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse bearerAuth(String token) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/bearer")
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse bearerAuthMissing() {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/bearer")
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse getJson() {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/json")
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse postData(String data) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/post")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(data)
                .build();
        ApiResponse resp = requestMethod.post(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse getUuid() {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/uuid")
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }
}
