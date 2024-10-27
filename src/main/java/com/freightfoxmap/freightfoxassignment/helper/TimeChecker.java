package com.freightfoxmap.freightfoxassignment.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TimeChecker {

    public long getCurrentTimeAsLong() {
        return Instant.now().toEpochMilli();
    }

    public boolean isCurrentTimeGreaterThanFourHours(long givenTime) {
        long fourHoursInMillis = 4 * 60 * 60 * 1000;

        return (getCurrentTimeAsLong() - givenTime) > fourHoursInMillis;
    }
}
