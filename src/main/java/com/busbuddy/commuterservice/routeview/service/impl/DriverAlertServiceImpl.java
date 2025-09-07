package com.busbuddy.commuterservice.routeview.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.busbuddy.commuterservice.common.constants.ErrorCodes;
import com.busbuddy.commuterservice.common.exception.BusinessException;
import com.busbuddy.commuterservice.routeview.model.DriverAlert;
import com.busbuddy.commuterservice.routeview.model.Location;
import com.busbuddy.commuterservice.routeview.model.Route;
import com.busbuddy.commuterservice.routeview.model.RouteAssignment;
import com.busbuddy.commuterservice.routeview.model.RouteStop;
import com.busbuddy.commuterservice.routeview.model.Stop;
import com.busbuddy.commuterservice.routeview.repository.DriverAlertRepository;
import com.busbuddy.commuterservice.routeview.repository.LocationRepository;
import com.busbuddy.commuterservice.routeview.repository.RouteAssignmentRepository;
import com.busbuddy.commuterservice.routeview.repository.RouteStopRepository;
import com.busbuddy.commuterservice.routeview.repository.StopRepository;
import com.busbuddy.commuterservice.routeview.service.DriverAlertService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverAlertServiceImpl implements DriverAlertService {

    private final DriverAlertRepository driverAlertRepository;
    private final StopRepository stopRepository;
    private final RouteStopRepository routeStopRepository;
    private final LocationRepository locationRepository;
    private final RouteAssignmentRepository routeAssignmentRepository;

    @Override
    public String raiseDriverAlert(String stopName, String officeLocationName, String employeeId) {
        try {
            // 1. Find Stop
            Stop stop = stopRepository.findByStopNameIgnoreCase(stopName)
                    .orElseThrow(() -> new BusinessException(ErrorCodes.STOP_NOT_FOUND,
                            "Stop not found: " + stopName));

            // 2. Find RouteStop (Stop -> Route mapping)
            RouteStop routeStop = routeStopRepository.findByStop(stop)
                    .orElseThrow(() -> new BusinessException(ErrorCodes.ROUTE_NOT_FOUND,
                            "No route mapped for stop: " + stopName));

            Route route = routeStop.getRoute();

            // 3. Find Location
            Location location = locationRepository.findByLocationNameIgnoreCase(officeLocationName)
                    .orElseThrow(() -> new BusinessException(ErrorCodes.LOCATION_NOT_FOUND,
                            "Location not found: " + officeLocationName));

            // 4. Find Route Assignments (could be multiple)
            List<RouteAssignment> assignments = routeAssignmentRepository.findByRouteAndLocation(route, location);

            if (assignments.isEmpty()) {
                throw new BusinessException(ErrorCodes.ROUTE_ASSIGNMENT_NOT_FOUND,
                        "No assignment found for route " + route.getRouteName()
                                + " and location " + officeLocationName);
            }

            // pick the first assignment (or apply logic if multiple)
            RouteAssignment assignment = assignments.get(0);

            String busRegNumber = assignment.getBus().getRegNumber();
            String driverName = assignment.getDriver().getDriverName();

            // 5. Save Alert Log
            DriverAlert alert = DriverAlert.builder()
                    .stopName(stopName.trim())
                    .officeLocationName(officeLocationName.trim())
                    .busRegNumber(busRegNumber)
                    .driverName(driverName)
                    .createdAt(LocalDateTime.now())
                    .employeeId(employeeId)
                    .build();

            driverAlertRepository.save(alert);

            // 6. Return Confirmation
            return String.format("Alert sent to Bus %s (Driver: %s)", busRegNumber, driverName);

        } catch (BusinessException be) {
            throw be; // handled by centralized exception handler
        } catch (Exception e) {
            log.error("Error raising driver alert for stop={} location={}", stopName, officeLocationName, e);
            throw new RuntimeException("Unexpected error while raising driver alert");
        }
    }
}