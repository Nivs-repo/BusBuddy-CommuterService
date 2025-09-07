package com.busbuddy.commuterservice.common.util;

import com.busbuddy.commuterservice.common.dto.ApiResponse;
import com.busbuddy.commuterservice.common.dto.ApiResponse.ErrorDetail;

import lombok.extern.slf4j.Slf4j;


import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
public class ResponseBuilder {

    // Build error response
    public static <T> ResponseEntity<ApiResponse<T>> buildErrorResponse(int status, List<ErrorDetail> errors) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setResponseCode("111");

        ApiResponse.ResponseBody<T> body = new ApiResponse.ResponseBody<>();
        body.setErrors(errors);
        response.setResponseBody(body);

        return ResponseEntity.status(status).body(response);
    }

    // Build success response
    public static <T> ResponseEntity<ApiResponse<T>> buildSuccessResponse(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setResponseCode("000");

        ApiResponse.ResponseBody<T> body = new ApiResponse.ResponseBody<>();
        body.setPayload(data);

        response.setResponseBody(body);

        return ResponseEntity.ok(response);
    }
}
