package com.freightfoxmap.freightfoxassignment.model;

import jakarta.persistence.*;

import java.util.HashMap;

@Entity
@Table(name="weather_info", indexes = {
        @Index(name = "lat_lon_time", columnList= "lat, lon")
})
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double lat;
    private double lon;

    private long time;

    private String weather;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Weather() {
    }

    public Weather(double lat, double lon, long time, String weather) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
        this.weather = weather;
    }

}
