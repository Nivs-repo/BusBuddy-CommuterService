package com.busbuddy.commuterservice.routeview.service.impl;

import com.busbuddy.commuterservice.common.constants.ErrorCodes;
import com.busbuddy.commuterservice.common.exception.BusinessException;
import com.busbuddy.commuterservice.common.exception.TechnicalException;
import com.busbuddy.commuterservice.routeview.dto.BusAtStopResponse;
import com.busbuddy.commuterservice.routeview.model.Location;
import com.busbuddy.commuterservice.routeview.model.RouteStop;
import com.busbuddy.commuterservice.routeview.repository.LocationRepository;
import com.busbuddy.commuterservice.routeview.repository.RouteAssignmentRepository;
import com.busbuddy.commuterservice.routeview.repository.RouteStopRepository;
import com.busbuddy.commuterservice.routeview.service.RouteViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteViewServiceImpl implements RouteViewService {

    private final RouteStopRepository routeStopRepository;
    private final RouteAssignmentRepository routeAssignmentRepository;
    private final LocationRepository locationRepository;

    @Override
    public List<BusAtStopResponse> getBusesByLocationAndStop(String locationName, String stopName) {
        try {
            // 1. Validate location
            Location location = locationRepository.findByLocationNameIgnoreCase(locationName.trim())
                    .orElseThrow(() -> new BusinessException(
                            ErrorCodes.LOCATION_NOT_FOUND,
                            "Location not found: " + locationName));

            // 2. Find route stops for the stop name
            List<RouteStop> routeStops = routeStopRepository.findByStop_StopNameIgnoreCase(stopName.trim());
            if (routeStops.isEmpty()) {
                throw new BusinessException(
                        ErrorCodes.STOP_NOT_FOUND,
                        "Stop not found: " + stopName);
            }

            // 3. For each route stop → check assignments for this location
            return routeStops.stream()
                    .flatMap(rs -> routeAssignmentRepository.findByRouteAndLocation(rs.getRoute(), location)
                            .stream()
                            .map(ra -> BusAtStopResponse.builder()
                                    .busRegNumber(ra.getBus().getRegNumber())
                                    .routeName(rs.getRoute().getRouteName())
                                    .origin(rs.getRoute().getOrigin())
                                    .destination(rs.getRoute().getDestination())
                                    .stopSequence(rs.getSequenceNumber())
                                    .build()
                            )
                    )
                    .collect(Collectors.toList());

        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            log.error("Error fetching buses for location={} stop={}", locationName, stopName, e);
            throw new TechnicalException(ErrorCodes.TECHNICAL_ERROR, "Unable to fetch buses");
        }
    }
}
