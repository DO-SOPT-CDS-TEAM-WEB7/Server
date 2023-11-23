package org.third.thirdseminar.controller.dto.request;

import java.util.Date;

public record CreateReservationRequest(
        Date startDate,
        Date endDate,
        Long ticketId
){

}
