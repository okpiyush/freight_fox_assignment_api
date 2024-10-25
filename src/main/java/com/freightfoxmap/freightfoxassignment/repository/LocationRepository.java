package com.freightfoxmap.freightfoxassignment.repository;

import com.freightfoxmap.freightfoxassignment.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {

}
