package com.busbuddy.commuterservice.routeview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class BusAtStopResponse {

    private String busRegNumber;   // TN-01-1234
    private String routeName;      // Siruseri Express
    private String origin;         // Sholinganallur
    private String destination;    // TCS Siruseri
    private Integer stopSequence;  // 2
}
