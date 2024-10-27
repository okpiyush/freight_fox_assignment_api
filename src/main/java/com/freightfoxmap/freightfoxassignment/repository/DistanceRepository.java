package com.freightfoxmap.freightfoxassignment.repository;

import com.freightfoxmap.freightfoxassignment.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistanceRepository extends JpaRepository<Distance, String> {
    Optional<Distance> findByOriginPincodeAndDestinationPincode(int originPincode, int destinationPincode);
}
