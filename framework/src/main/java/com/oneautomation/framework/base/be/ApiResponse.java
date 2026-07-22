package com.oneautomation.framework.base.be;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Map;

public class ApiResponse {

    private final int statusCode;
    private final byte[] responseData;
    private final Response response;

    public ApiResponse(Response response) {
        this.response = response;
        this.statusCode = response.getStatusCode();
        this.responseData = response.getBody().asByteArray();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return new String(responseData);
    }

    public byte[] getResponseData() {
        return responseData;
    }

    public JsonPath jsonPath() {
        return response.jsonPath();
    }

    public String getResponseHeader(String headerName) {
        return response.getHeader(headerName);
    }

    public Map<String, String> getCookies() {
        return response.getCookies();
    }

    public Response getRawResponse() {
        return response;
    }
}
