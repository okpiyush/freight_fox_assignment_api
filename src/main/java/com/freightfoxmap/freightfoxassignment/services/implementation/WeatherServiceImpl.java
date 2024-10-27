package com.freightfoxmap.freightfoxassignment.services.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.freightfoxmap.freightfoxassignment.config.GetWeather;
import com.freightfoxmap.freightfoxassignment.helper.TimeChecker;
import com.freightfoxmap.freightfoxassignment.model.Location;
import com.freightfoxmap.freightfoxassignment.model.Weather;
import com.freightfoxmap.freightfoxassignment.repository.WeatherRepository;
import com.freightfoxmap.freightfoxassignment.services.WeatherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeatherServiceImpl implements WeatherServices {

    private final TimeChecker time;
    private final WeatherRepository weather;
    private final LocationServiceImpl locationService;

    @Autowired
    public WeatherServiceImpl( TimeChecker time1, WeatherRepository weather, LocationServiceImpl locationService) {
        this.time = time1;
        this.locationService = locationService;
        this.weather = weather;
    }


    private Weather searchApiForWeatherData(Location location){
        double lat = location.getLat();
        double lon = location.getLon();
        //API Call
        GetWeather getWeather = new GetWeather(location.getLat(),location.getLon());
        //further processing
        JsonNode weatherInfo = getWeather.getWeather();
        String weatherDescription = weatherInfo.path("data").get(0).path("weather").path("description").asText();
        long curr_time = time.getCurrentTimeAsLong();
        Weather latest_weather =  new Weather(lat, lon, curr_time, weatherDescription);
        return addWeather(latest_weather);
    }

    private Weather searchLocalDBForWeatherElseAPI(Location location){
        double lat = location.getLat();
        double lon = location.getLon();
        // find weather inside the Database
        Weather lastWeather = weather.findLatestByLatAndLon(lat, lon);
        if (lastWeather == null) return null;
        if (time.isCurrentTimeGreaterThanFourHours(lastWeather.getTime())){
            return searchApiForWeatherData(location);
        }
        return lastWeather;
    }

    private Weather addWeather(Weather w){
        return weather.save(w);
    }



    @Override
    public Weather getWeather(int pincode) {
        Location l = locationService.getLocation(pincode);
        Weather latest_weather = searchLocalDBForWeatherElseAPI(l);
        if (latest_weather == null) return searchApiForWeatherData(l);
        return latest_weather;
    }
}
