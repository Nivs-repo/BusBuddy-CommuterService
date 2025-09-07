package com.busbuddy.commuterservice.routeview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busbuddy.commuterservice.routeview.model.Location;
import com.busbuddy.commuterservice.routeview.model.Route;
import com.busbuddy.commuterservice.routeview.model.RouteAssignment;

import java.util.List;

@Repository
public interface RouteAssignmentRepository extends JpaRepository<RouteAssignment, Long> {

    // Find all route assignments by route + location
    List<RouteAssignment> findByRouteAndLocation(Route route, Location location);
}
