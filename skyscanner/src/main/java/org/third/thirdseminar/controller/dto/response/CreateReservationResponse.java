package org.third.thirdseminar.controller.dto.response;

import java.time.LocalTime;

public record CreateReservationResponse(
        LocalTime startDateTime,
        LocalTime endDateTime,
        String price
){
    public static CreateReservationResponse of(LocalTime startDateTime,
                                               LocalTime endDateTime,
                                               String price){
        return new CreateReservationResponse(startDateTime, endDateTime, price);
    }
}
