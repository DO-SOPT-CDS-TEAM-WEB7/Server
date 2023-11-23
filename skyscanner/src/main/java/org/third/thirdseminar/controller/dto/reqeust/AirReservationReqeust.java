package org.third.thirdseminar.controller.dto.reqeust;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record AirReservationReqeust (
        Date startDate,
        Date endDate
){
}