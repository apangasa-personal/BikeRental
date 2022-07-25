package com.vehicleRental.app.enums;

public enum VehicleStatus {
    AVAILABLE,
    BOOKED,
    NOTSERVICABLE,
    DEFAULT;

    private static VehicleStatus[] allValues = values();
    public static VehicleStatus fromOrdinal(int n) {return allValues[n];}
}
