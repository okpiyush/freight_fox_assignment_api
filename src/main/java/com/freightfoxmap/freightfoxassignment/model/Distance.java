package com.freightfoxmap.freightfoxassignment.model;


import jakarta.persistence.*;

@Entity
@Table(name="distance_between", indexes = {
        @Index(name= "pincode_index", columnList = "originPincode, destinationPincode")
})
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int originPincode;
    private int destinationPincode;
    private int distance;

    public int getOriginPincode() {
        return originPincode;
    }

    public void setOriginPincode(int originPincode) {
        this.originPincode = originPincode;
    }

    public int getDestinationPincode() {
        return destinationPincode;
    }

    public void setDestinationPincode(int destinationPincode) {
        this.destinationPincode = destinationPincode;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Distance() {
    }

    public Distance(int originPincode, int destinationPincode, int distance) {
        this.originPincode = originPincode;
        this.destinationPincode = destinationPincode;
        this.distance = distance;
    }
}
