package com.oneautomation.framework.base.be;

public interface HttpClient {

    ApiResponse get(ApiRequestSpec spec);

    ApiResponse post(ApiRequestSpec spec);

    ApiResponse put(ApiRequestSpec spec);

    ApiResponse delete(ApiRequestSpec spec);

    ApiResponse patch(ApiRequestSpec spec);

    ApiResponse head(ApiRequestSpec spec);
}
