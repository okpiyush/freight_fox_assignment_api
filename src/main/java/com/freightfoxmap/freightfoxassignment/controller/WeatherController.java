package com.freightfoxmap.freightfoxassignment.controller;

import com.freightfoxmap.freightfoxassignment.model.Weather;
import com.freightfoxmap.freightfoxassignment.services.implementation.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherServiceImpl weatherService;

    @Autowired
    public WeatherController(WeatherServiceImpl weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("zip={zip_code}")
    public Weather currentWeatherInformation(@PathVariable("zip_code") int pincode){
        return weatherService.getWeather(pincode);

    }

}
