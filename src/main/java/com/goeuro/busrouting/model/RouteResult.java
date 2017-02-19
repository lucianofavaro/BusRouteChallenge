package com.goeuro.busrouting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteResult {
    int departureId;
    int arrivalId;
    boolean directBusRoute;

    public RouteResult(int departureId, int arrivalId, boolean directBusRoute) {
        this.departureId = departureId;
        this.arrivalId = arrivalId;
        this.directBusRoute = directBusRoute;
    }

    @JsonProperty(value = "dep_sid")
    public int getDepartureId() {
        return departureId;
    }

    public void setDepartureId(int departureId) {
        this.departureId = departureId;
    }

    @JsonProperty(value = "arr_sid")
    public int getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(int arrivalId) {
        this.arrivalId = arrivalId;
    }

    @JsonProperty(value = "direct_bus_route")
    public boolean isDirectBusRoute() {
        return directBusRoute;
    }

    public void setDirectBusRoute(boolean directBusRoute) {
        this.directBusRoute = directBusRoute;
    }

}