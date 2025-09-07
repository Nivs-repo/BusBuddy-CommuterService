package com.busbuddy.commuterservice.routeview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busbuddy.commuterservice.routeview.model.Stop;

import java.util.Optional;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {

    // Case-insensitive search by stop name
    Optional<Stop> findByStopNameIgnoreCase(String stopName);
}
