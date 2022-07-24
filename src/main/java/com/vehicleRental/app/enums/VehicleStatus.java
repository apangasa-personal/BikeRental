package com.vehicleRental.app.enums;

public enum VehicleStatus {
    AVAILABLE("available"),
    BOOKED("booked"),
    NOTSERVICABLE("not_servicable"),
    DEFAULT("default");

    private final String name;

    private VehicleStatus(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}
