package com.oneautomation.tests.pageobjects.api;

import com.oneautomation.framework.base.be.ApiBase;
import com.oneautomation.framework.base.be.ApiRequestSpec;
import com.oneautomation.framework.base.be.ApiResponse;
import com.oneautomation.tests.common.EndpointResolver;

import java.util.HashMap;
import java.util.Map;

public class UserApiPO extends ApiBase {

    public ApiResponse getUsers() {
        ApiRequestSpec spec = requestBuild()
                .endpoint(EndpointResolver.getApiURL(""))
                .basePath("/users")
                .header("Content-Type", "application/json")
                .build();
        ApiResponse apiResponse = requestMethod.get(spec);
        saveResponse(apiResponse);
        return apiResponse;
    }

    public ApiResponse getUserById(String userId) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(EndpointResolver.getApiURL(""))
                .basePath("/users/" + userId)
                .header("Content-Type", "application/json")
                .build();
        ApiResponse apiResponse = requestMethod.get(spec);
        saveResponse(apiResponse);
        return apiResponse;
    }

    public ApiResponse createUser(String name, String job) {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("job", job);

        ApiRequestSpec spec = requestBuild()
                .endpoint(EndpointResolver.getApiURL(""))
                .basePath("/users")
                .header("Content-Type", "application/json")
                .body(body)
                .build();
        ApiResponse apiResponse = requestMethod.post(spec);
        saveResponse(apiResponse);
        return apiResponse;
    }

    public ApiResponse deleteUser(String userId) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(EndpointResolver.getApiURL(""))
                .basePath("/users/" + userId)
                .build();
        ApiResponse apiResponse = requestMethod.delete(spec);
        saveResponse(apiResponse);
        return apiResponse;
    }
}
