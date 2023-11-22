package org.third.thirdseminar.controller.dto.response;

import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.domain.TimeRange;

public record AirDto (
        Long airId,
        String airName,
        TimeRange startTime,
        TimeRange endTime,
        Long price,
        int CO2
){
    public static AirDto of(Reservation reservation, Long price) {
        return new AirDto(
                reservation.getId(),
                reservation.getAir().getAirName(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                price,
                reservation.getAir().getCO2()
        );
    }
}