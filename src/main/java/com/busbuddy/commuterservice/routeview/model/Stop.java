package com.busbuddy.commuterservice.routeview.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "stop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_id")
    private Long stopId;

    @Column(name = "stop_name", length = 100, nullable = false)
    private String stopName;

    @Column(name = "gps_location", length = 100)
    private String gpsLocation;

    @Column(name = "landmark", length = 100)
    private String landmark;

    @Column(name = "image_url", length = 200)
    private String imageUrl;

}
