package com.freightfoxmap.freightfoxassignment.services;

import com.freightfoxmap.freightfoxassignment.model.Location;

public interface LocationServices {
    public Location getLocation(int pincode);
    public Location addLocation(int pincode, String lat, String lon);

}
