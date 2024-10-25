package com.freightfoxmap.freightfoxassignment.model;

import jakarta.persistence.*;

import java.util.HashMap;

@Entity
@Table(name="weather_info")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int lat;
    private int lon;

    private String time;

    private String weather;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public Weather(int id, int lat, int lon, String time, String weather) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
        this.weather = weather;
    }

}
