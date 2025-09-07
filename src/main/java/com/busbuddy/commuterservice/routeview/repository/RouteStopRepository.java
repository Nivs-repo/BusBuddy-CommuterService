package com.busbuddy.commuterservice.routeview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busbuddy.commuterservice.routeview.model.RouteStop;
import com.busbuddy.commuterservice.routeview.model.Stop;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteStopRepository extends JpaRepository<RouteStop, Long> {

    // Find all stops by stop name
    List<RouteStop> findByStop_StopNameIgnoreCase(String stopName);

    Optional<RouteStop> findByStop(Stop stop);
}
