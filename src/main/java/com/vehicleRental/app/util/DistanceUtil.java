package com.vehicleRental.app.util;

public class DistanceUtil {

    public static Double distance(Double lat1, Double lon1, Double lat2, Double lon2) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        return Math.abs(lat1 + lon1 - lat2 - lon2);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static Double deg2rad(Double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static Double rad2deg(Double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
