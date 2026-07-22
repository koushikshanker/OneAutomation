package com.oneautomation.tests.pageobjects.api;

import com.oneautomation.framework.base.be.ApiBase;
import com.oneautomation.framework.base.be.ApiRequestSpec;
import com.oneautomation.framework.base.be.ApiResponse;

import java.util.HashMap;
import java.util.Map;

public class BookingApiPO extends ApiBase {

    private static final String BASE = "https://restful-booker.herokuapp.com";

    public ApiResponse healthCheck() {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/ping")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public String createAuthToken() {
        Map<String, String> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "password123");

        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/auth")
                .header("Content-Type", "application/json")
                .body(body)
                .build();
        ApiResponse resp = requestMethod.post(spec);
        saveResponse(resp);
        return resp.jsonPath().getString("token");
    }

    public ApiResponse getAllBookingIds() {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/booking")
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse getBookingById(int id) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/booking/" + id)
                .header("Accept", "application/json")
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse filterBookingsByName(String firstname, String lastname) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/booking")
                .header("Accept", "application/json")
                .queryParam("firstname", firstname)
                .queryParam("lastname", lastname)
                .build();
        ApiResponse resp = requestMethod.get(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse createBooking(String firstname, String lastname, int totalPrice,
                                     boolean depositPaid, String checkin, String checkout) {
        Map<String, Object> dates = new HashMap<>();
        dates.put("checkin", checkin);
        dates.put("checkout", checkout);

        Map<String, Object> body = new HashMap<>();
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", totalPrice);
        body.put("depositpaid", depositPaid);
        body.put("bookingdates", dates);
        body.put("additionalneeds", "Breakfast");

        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(body)
                .build();
        ApiResponse resp = requestMethod.post(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse updateBooking(int id, String token, String firstname, String lastname,
                                     int totalPrice, boolean depositPaid, String checkin, String checkout) {
        Map<String, Object> dates = new HashMap<>();
        dates.put("checkin", checkin);
        dates.put("checkout", checkout);

        Map<String, Object> body = new HashMap<>();
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", totalPrice);
        body.put("depositpaid", depositPaid);
        body.put("bookingdates", dates);
        body.put("additionalneeds", "Lunch");

        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/booking/" + id)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .cookie("token", token)
                .body(body)
                .build();
        ApiResponse resp = requestMethod.put(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse partialUpdateBooking(int id, String token, String firstname) {
        Map<String, String> body = new HashMap<>();
        body.put("firstname", firstname);

        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/booking/" + id)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .cookie("token", token)
                .body(body)
                .build();
        ApiResponse resp = requestMethod.patch(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse deleteBooking(int id, String token) {
        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/booking/" + id)
                .header("Content-Type", "application/json")
                .cookie("token", token)
                .build();
        ApiResponse resp = requestMethod.delete(spec);
        saveResponse(resp);
        return resp;
    }

    public ApiResponse updateBookingWithBasicAuth(int id, String firstname, String lastname,
                                                  int totalPrice, boolean depositPaid,
                                                  String checkin, String checkout) {
        Map<String, Object> dates = new HashMap<>();
        dates.put("checkin", checkin);
        dates.put("checkout", checkout);

        Map<String, Object> body = new HashMap<>();
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", totalPrice);
        body.put("depositpaid", depositPaid);
        body.put("bookingdates", dates);

        ApiRequestSpec spec = requestBuild()
                .endpoint(BASE)
                .basePath("/booking/" + id)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(body)
                .build();
        ApiResponse resp = requestMethod.put(spec);
        saveResponse(resp);
        return resp;
    }
}
