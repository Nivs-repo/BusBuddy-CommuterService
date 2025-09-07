package com.busbuddy.commuterservice.routeview.dto;

import jakarta.validation.constraints.NotBlank;
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
public class DriverAlertRequest {

    @NotBlank(message = "Stop name is required")
    private String stopName;

    @NotBlank(message = "Office location name is required")
    private String officeLocationName;

    @NotBlank(message = "employee id is required")
    private String employeeId;
}
