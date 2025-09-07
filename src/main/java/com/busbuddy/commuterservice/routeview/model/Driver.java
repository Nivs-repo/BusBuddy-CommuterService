package com.busbuddy.commuterservice.routeview.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Entity
@Table(name = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "driver_name", length = 100)
    private String driverName;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Column(name = "license_number", length = 50)
    private String licenseNumber;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "gov_id", length = 50)
    private String govId;

    @Column(name = "is_active", length = 1)
    private String isActive = "Y"; // 'Y' or 'N'
}
