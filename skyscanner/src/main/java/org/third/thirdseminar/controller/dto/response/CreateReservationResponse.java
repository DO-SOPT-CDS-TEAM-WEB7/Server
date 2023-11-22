package org.third.thirdseminar.controller.dto.response;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record CreateReservationResponse(
        String startDateTime,
        String endDateTime,
        String price
){
    public static CreateReservationResponse of(String startDateTime,
                                               String endDateTime,
                                               String price){

        return new CreateReservationResponse(startDateTime,endDateTime, price);
    }
}
