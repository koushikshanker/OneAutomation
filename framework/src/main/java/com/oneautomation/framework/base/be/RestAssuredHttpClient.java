package com.oneautomation.framework.base.be;

import com.oneautomation.framework.config.ConfigMap;
import com.oneautomation.framework.config.PropertyUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestAssuredHttpClient implements HttpClient {

    private static final Logger log = LoggerFactory.getLogger(RestAssuredHttpClient.class);

    private io.restassured.specification.RequestSpecification buildSpec(ApiRequestSpec spec) {
        String baseUri = spec.getEndpoint();
        if (baseUri == null || baseUri.isEmpty()) {
            baseUri = PropertyUtils.get(ConfigMap.API_BASE_URL);
        }

        io.restassured.specification.RequestSpecification raSpec = RestAssured.given()
                .baseUri(baseUri);

        if (spec.getBasePath() != null && !spec.getBasePath().isEmpty()) {
            raSpec.basePath(spec.getBasePath());
        }

        if (!spec.getHeaders().isEmpty()) {
            raSpec.headers(spec.getHeaders());
        }

        if (spec.getBody() != null) {
            raSpec.body(spec.getBody());
        }

        if (!spec.getQueryParams().isEmpty()) {
            raSpec.queryParams(spec.getQueryParams());
        }

        if (!spec.getCookies().isEmpty()) {
            raSpec.cookies(spec.getCookies());
        }

        return raSpec;
    }

    @Override
    public ApiResponse get(ApiRequestSpec spec) {
        log.info("Executing GET: {} {}", spec.getEndpoint(), spec.getBasePath());
        Response response = buildSpec(spec).get();
        return new ApiResponse(response);
    }

    @Override
    public ApiResponse post(ApiRequestSpec spec) {
        log.info("Executing POST: {} {}", spec.getEndpoint(), spec.getBasePath());
        Response response = buildSpec(spec).post();
        return new ApiResponse(response);
    }

    @Override
    public ApiResponse put(ApiRequestSpec spec) {
        log.info("Executing PUT: {} {}", spec.getEndpoint(), spec.getBasePath());
        Response response = buildSpec(spec).put();
        return new ApiResponse(response);
    }

    @Override
    public ApiResponse delete(ApiRequestSpec spec) {
        log.info("Executing DELETE: {} {}", spec.getEndpoint(), spec.getBasePath());
        Response response = buildSpec(spec).delete();
        return new ApiResponse(response);
    }

    @Override
    public ApiResponse patch(ApiRequestSpec spec) {
        log.info("Executing PATCH: {} {}", spec.getEndpoint(), spec.getBasePath());
        Response response = buildSpec(spec).patch();
        return new ApiResponse(response);
    }

    @Override
    public ApiResponse head(ApiRequestSpec spec) {
        log.info("Executing HEAD: {} {}", spec.getEndpoint(), spec.getBasePath());
        Response response = buildSpec(spec).head();
        return new ApiResponse(response);
    }
}
