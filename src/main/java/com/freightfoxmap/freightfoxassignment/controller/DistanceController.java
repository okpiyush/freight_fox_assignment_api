package com.freightfoxmap.freightfoxassignment.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.freightfoxmap.freightfoxassignment.model.Distance;
import com.freightfoxmap.freightfoxassignment.services.DistanceServices;
import com.freightfoxmap.freightfoxassignment.services.implementation.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distance")
public class DistanceController {

    private final DistanceServices distanceServices;
    private final LocationServiceImpl locationServices;

    @Autowired
    public DistanceController(DistanceServices distanceServices, LocationServiceImpl locationServices) {
        this.distanceServices = distanceServices;
        this.locationServices = locationServices;
    }

    @GetMapping
    public JsonNode testDistance() {
        return locationServices.findLocation(140401);
        // return new Distance();
    }

    @GetMapping("/pol={pol_zip}&&pod={pod_zip}")
    public Distance findDistance(@PathVariable("pol_zip") int pol_zip, @PathVariable("pod_zip") int pod_zip) {

        return distanceServices.findDistance(pol_zip, pod_zip);
    }
}
