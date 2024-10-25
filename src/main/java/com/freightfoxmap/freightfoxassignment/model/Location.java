package com.freightfoxmap.freightfoxassignment.model;


import jakarta.persistence.*;

@Entity
@Table(name="location_info")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pincode;
    private double lat;
    private double lon;

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Location() {
    }

    public Location(int pincode, double lat, double lon) {
        this.pincode = pincode;
        this.lat = lat;
        this.lon = lon;
    }
}
