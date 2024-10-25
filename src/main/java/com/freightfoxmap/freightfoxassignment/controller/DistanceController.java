package com.freightfoxmap.freightfoxassignment.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.freightfoxmap.freightfoxassignment.model.Distance;
import com.freightfoxmap.freightfoxassignment.services.DistanceServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distance")
public class DistanceController {
    DistanceServices distanceServices = new DistanceServices();

    @GetMapping
    public Distance testDistance(){
//        distanceServices.findDistanceFromApi("30.479189365350788","76.59357250628858","12.895886327008034","77.57464858015659");
        return new Distance();
    }
    @GetMapping("/pol={pol_zip}&&pod={pod_zip}")
    public Distance findDistance(@PathVariable("pol_zip") int pol_zip, @PathVariable("pod_zip") int pod_zip){

        return new Distance();
    }
}
