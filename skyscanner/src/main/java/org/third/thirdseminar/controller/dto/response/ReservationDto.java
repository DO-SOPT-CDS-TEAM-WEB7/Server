package org.third.thirdseminar.controller.dto.response;

import org.third.thirdseminar.domain.Reservation;

public record ReservationDto (
        Long reservationId,
        String airName,
        TimeRangeDto startTime,
        TimeRangeDto endTime,
        String price,
        int CO2
){
    public static ReservationDto of(Reservation reservation, String price, TimeRangeDto startTime, TimeRangeDto endTime) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getAir().getAirName(),
                startTime,
                endTime,
                price,
                reservation.getAir().getCO2()
        );
    }
}