package com.freightfoxmap.freightfoxassignment.repository;

import com.freightfoxmap.freightfoxassignment.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, String> {
    @Query("SELECT l FROM Location l WHERE l.pincode = :pincode")
    Optional<Location> findByPincode(@Param("pincode") int pincode);
}
