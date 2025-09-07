package com.busbuddy.commuterservice.routeview.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity
@Table(name = "driver_alert")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class DriverAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @Column(nullable = false)
    private String employeeId;

    @Column(nullable = false)
    private String stopName;

    @Column(nullable = false)
    private String officeLocationName;

    @Column(nullable = false)
    private String busRegNumber;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
