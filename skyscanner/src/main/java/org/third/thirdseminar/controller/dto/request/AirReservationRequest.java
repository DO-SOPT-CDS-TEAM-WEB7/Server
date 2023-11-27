package org.third.thirdseminar.controller.dto.request;

import java.util.Date;

public record AirReservationRequest(
        Date startDate,
        Date endDate
){
}