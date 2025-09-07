package com.busbuddy.commuterservice.routeview.controller;

import com.busbuddy.commuterservice.common.util.ResponseBuilder;
import com.busbuddy.commuterservice.routeview.service.RouteViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commuter")
@RequiredArgsConstructor
@Slf4j
public class RouteViewController {

    private final RouteViewService routeViewService;

    /**
     * Get buses available for a location and stop
     */
    @GetMapping("/locations/{locationName}/stops/{stopName}/buses")
    public ResponseEntity<?> getBusesAtStop(
            @PathVariable String locationName,
            @PathVariable String stopName) {

        return ResponseBuilder.buildSuccessResponse(
                routeViewService.getBusesByLocationAndStop(locationName, stopName)
        );
    }
}