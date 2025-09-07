package com.busbuddy.commuterservice.routeview.service;


import java.util.List;

import com.busbuddy.commuterservice.routeview.dto.BusAtStopResponse;

public interface RouteViewService {

    List<BusAtStopResponse> getBusesByLocationAndStop(String locationName, String stopName);

}
