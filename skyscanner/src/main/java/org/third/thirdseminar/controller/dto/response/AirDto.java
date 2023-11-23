package org.third.thirdseminar.controller.dto.response;

import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.domain.TimeRange;

import java.sql.Time;

public record AirDto (
        Long airId,
        String airName,
        TimeRangeDto startTime,
        TimeRangeDto endTime,
        String price,
        int CO2
){
    public static AirDto of(Reservation reservation, String price, TimeRangeDto startTime, TimeRangeDto endTime) {
        return new AirDto(
                reservation.getId(),
                reservation.getAir().getAirName(),
                startTime,
                endTime,
                price,
                reservation.getAir().getCO2()
        );
    }
}