package com.freightfoxmap.freightfoxassignment.services.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.freightfoxmap.freightfoxassignment.config.GetLocation;
import com.freightfoxmap.freightfoxassignment.model.Location;
import com.freightfoxmap.freightfoxassignment.repository.LocationRepository;
import com.freightfoxmap.freightfoxassignment.services.LocationServices;
import com.sun.source.doctree.SystemPropertyTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Optional;


@Service
public class LocationServiceImpl implements LocationServices {
    LocationRepository locationRepo;

    @Autowired
    public LocationServiceImpl(LocationRepository lr){
        this.locationRepo=lr;
    }


    private double convertStringToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            System.out.println("Invalid string for conversion: " + str);
            return 0.0;
        }
    }


    private JsonNode findLocationLatLonUsingApi(int pincode){
        System.out.println(pincode);
        GetLocation locationApi = new GetLocation(pincode);
        JsonNode location =  locationApi.getLatLong();
        return location;
    }

    public JsonNode findLocation(int pincode){
        return findLocationLatLonUsingApi(pincode);

    }
    @Override
    public Location getLocation(int pincode) {
        System.out.println(pincode);
        Optional<Location> location = locationRepo.findByPincode(pincode);
        if (location.isEmpty()){
            JsonNode locations = findLocationLatLonUsingApi(pincode);
            if (locations == null) return null;
            return addLocation(pincode,locations.get("latitude").asText(),locations.get("longitude").asText());
        }
        return location.get();
    }

    @Override
    public Location addLocation(int pincode, String lat, String lon) {
        Location location =  new Location(pincode,convertStringToDouble(lat),convertStringToDouble(lon));
        System.out.println("------------------------------------");
        System.out.println(location.getLat());
        System.out.println(location.getLon());
        System.out.println(location.getPincode());
        System.out.println("------------------------------------");
        locationRepo.save(location);
        return location;
    }
}

