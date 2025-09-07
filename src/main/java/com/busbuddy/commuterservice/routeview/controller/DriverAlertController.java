package com.busbuddy.commuterservice.routeview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.busbuddy.commuterservice.common.util.ResponseBuilder;
import com.busbuddy.commuterservice.routeview.dto.DriverAlertRequest;
import com.busbuddy.commuterservice.routeview.service.DriverAlertService;

import java.util.Map;

@RestController
@RequestMapping("/api/commuter/alerts")
@RequiredArgsConstructor
@Slf4j
public class DriverAlertController {

    private final DriverAlertService driverAlertService;

    @PostMapping
    public ResponseEntity<?> raiseAlert(@Valid @RequestBody DriverAlertRequest request) {
        String message = driverAlertService.raiseDriverAlert(
                request.getStopName(),
                request.getOfficeLocationName(), 
                request.getEmployeeId()
        );

        return ResponseBuilder.buildSuccessResponse(Map.of("message", message));
    }
}
