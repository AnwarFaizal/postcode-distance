/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pejal.wcc.postcodedistance.controller;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author pejalhebat
 */
@Slf4j
public class DistanceCalculator {
    private final static double EARTH_RADIUS = 6371; // radius in kilometers

    public double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {
        // Using Haversine formula! See Wikipedia;
        log.info("\nA(" + latitude + "," + longitude + ") \nB("+ latitude2 + "," + longitude2 + ")");
        double lon1Radians = Math.toRadians(longitude);
        double lon2Radians = Math.toRadians(longitude2);
        double lat1Radians = Math.toRadians(latitude);
        double lat2Radians = Math.toRadians(latitude2);
        double a = haversine(lat1Radians, lat2Radians)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        log.info("\nlon1Radians: " + lon1Radians + "\nlon2Radians: " + lon2Radians
        + "\nlat1Radians: " + lat1Radians + "\nlat2Radians: " + lat2Radians + "\na: " + a);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        log.info("Distance calculated is: " + (EARTH_RADIUS * c) );
        return (EARTH_RADIUS * c);
    }

    private double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    private double square(double x) {
        return x * x;
    }
}
