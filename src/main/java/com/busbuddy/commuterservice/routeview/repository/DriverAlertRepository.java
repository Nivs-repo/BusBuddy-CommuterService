package com.busbuddy.commuterservice.routeview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busbuddy.commuterservice.routeview.model.DriverAlert;

@Repository
public interface DriverAlertRepository extends JpaRepository<DriverAlert, Long> {
    // Basic CRUD from JpaRepository is enough for logging alerts
}
