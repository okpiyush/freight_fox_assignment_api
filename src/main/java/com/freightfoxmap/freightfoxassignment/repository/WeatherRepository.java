package com.freightfoxmap.freightfoxassignment.repository;

import com.freightfoxmap.freightfoxassignment.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeatherRepository extends JpaRepository<Weather, String> {

    @Query("SELECT w FROM Weather w WHERE w.lat = :lat AND w.lon = :lon ORDER BY w.time DESC")
    Weather findLatestByLatAndLon(@Param("lat") double lat, @Param("lon") double lon);
}
