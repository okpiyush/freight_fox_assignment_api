package com.freightfoxmap.freightfoxassignment.services;

import com.freightfoxmap.freightfoxassignment.model.Weather;
import org.springframework.stereotype.Service;

@Service
public interface WeatherServices {


    public Weather getWeather(int pincode);
}
