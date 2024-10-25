package com.freightfoxmap.freightfoxassignment.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.freightfoxmap.freightfoxassignment.config.GetDistance;
import com.freightfoxmap.freightfoxassignment.model.Distance;
import org.springframework.stereotype.Service;

@Service
public class DistanceServices {

    private String parseJsonFindDistance(JsonNode rootNode){
        JsonNode rows = rootNode.path("rows");
        double distance = 0;
        if (rows.isArray()) {
            for (JsonNode row : rows) {
                JsonNode elements = row.path("elements");
                if (elements.isArray()) {
                    for (JsonNode element : elements) {
                        String distanceText = element.path("distance").path("text").asText();
                        distance = element.path("distance").path("value").asInt();
                        System.out.println("Distance: " + distanceText + " (" + distance + " meters)");

                        String durationText = element.path("duration").path("text").asText();
                        System.out.println("Duration: " + durationText);
                    }
                }
            }
        }
        distance /= 1000;
        return ""+distance;

    }
    private String findDistanceFromApi(String xlat, String xlon, String ylat, String ylon){
        GetDistance distanceObj = new GetDistance(xlat,xlon, ylat,ylon, "LGrsmhIeTWtYdhdAJy4UJKmrhbyf60SaV4EtrQ2FsLVEZbvxodIFlJrW2rgUsq7B");
        JsonNode distanceJson = distanceObj.getDistance();
        return parseJsonFindDistance(distanceJson);
    }

    public Distance findDistance(int pincode1, int pincode2){
        return new Distance();
    }

}
