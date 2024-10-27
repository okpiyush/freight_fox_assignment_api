package com.freightfoxmap.freightfoxassignment.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.freightfoxmap.freightfoxassignment.config.GetDistance;
import com.freightfoxmap.freightfoxassignment.model.Distance;
import com.freightfoxmap.freightfoxassignment.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public interface DistanceServices {

    public Distance findDistance(int pincode1, int pincode2);

}
