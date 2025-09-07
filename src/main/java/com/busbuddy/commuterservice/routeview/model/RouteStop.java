package com.busbuddy.commuterservice.routeview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "route_stop",
       uniqueConstraints = @UniqueConstraint(columnNames = {"route_id", "stop_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    @JsonIgnore
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_id", nullable = false)
    
    private Stop stop;

    @Column(name = "sequence_number")
    private Integer sequenceNumber;
}
