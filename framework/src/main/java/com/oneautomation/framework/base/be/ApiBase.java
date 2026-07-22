package com.oneautomation.framework.base.be;

import com.oneautomation.framework.base.CommonAutomation;

public class ApiBase extends CommonAutomation {

    public static ThreadLocal<ApiResponse> response = new ThreadLocal<>();
    public static ThreadLocal<ApiRequestSpec.RequestBuilder> request = new ThreadLocal<>();
    public HttpClient requestMethod = new RestAssuredHttpClient();

    public void saveResponse(ApiResponse apiResponse) {
        response.set(apiResponse);
    }

    public ApiResponse apiResponse() {
        return response.get();
    }

    public ApiRequestSpec.RequestBuilder getRequestSpec() {
        return request.get();
    }

    public ApiRequestSpec.RequestBuilder requestBuild() {
        ApiRequestSpec.RequestBuilder builder = new ApiRequestSpec.RequestBuilder();
        request.set(builder);
        return builder;
    }

    public void clearRequestSpecificationAndResponseData() {
        request.remove();
        response.remove();
    }
}
