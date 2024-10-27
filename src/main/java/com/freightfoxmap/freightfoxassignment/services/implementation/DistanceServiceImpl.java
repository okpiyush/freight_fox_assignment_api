package com.freightfoxmap.freightfoxassignment.services.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.freightfoxmap.freightfoxassignment.config.GetDistance;
import com.freightfoxmap.freightfoxassignment.model.Distance;
import com.freightfoxmap.freightfoxassignment.model.Location;
import com.freightfoxmap.freightfoxassignment.repository.DistanceRepository;
import com.freightfoxmap.freightfoxassignment.repository.LocationRepository;
import com.freightfoxmap.freightfoxassignment.services.DistanceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class DistanceServiceImpl implements DistanceServices {

    private final DistanceRepository distance;
    private final LocationServiceImpl locationService;

    @Autowired
    public DistanceServiceImpl(DistanceRepository distance, LocationServiceImpl locationService) {
        this.distance = distance;
        this.locationService = locationService;
    }

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
    private Distance findDistanceInLocal(int pincode1, int pincode2){
       Optional<Distance> Distance = distance.findByOriginPincodeAndDestinationPincode(pincode1,pincode2);
       if (Distance.isPresent()){
           return Distance.get();
       }else {
           return null;
       }
    }

    private Distance findDistanceFromApiAndSave(int pincode1, int pincode2){
        if (pincode1 == pincode2){
            System.out.println("Cant Print Due to Same Pincode");
            return null;
        }
        if (String.valueOf(pincode1).isEmpty()) {
            System.out.println("Pincode 1 is missing");
            return null;
        }
        if (String.valueOf(pincode2).isEmpty()) {
            System.out.println("Pincode 2 is Missing");
            return null;
        }
        Distance curr_distance = findDistanceInLocal(pincode1,pincode2);
        if (curr_distance != null){
            return curr_distance;
        }else{
            //finding the lat and long for the pincodes
            Location origin = locationService.getLocation(pincode1);
            Location destination = locationService.getLocation(pincode2);
//            finding distance of the difference
            String diff_distance = findDistanceFromApi(
                    String.valueOf(origin.getLat()),
                    String.valueOf(origin.getLon()),
                    String.valueOf(destination.getLat()),
                    String.valueOf(destination.getLon())
            );
            diff_distance = String.valueOf(diff_distance).split("\\.")[0];
            Distance d = new Distance(pincode1,pincode2,Integer.parseInt(diff_distance));
            distance.save(d);
            return d;
        }

    }
    public Distance findDistance(int pincode1, int pincode2){
        return findDistanceFromApiAndSave(pincode1,pincode2);
    }
}
