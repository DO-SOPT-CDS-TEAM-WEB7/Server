package org.third.thirdseminar.controller.dto.reqeust;

import java.util.Date;

public record CreateReservationRequest(
        Date startDate,
        Date endDate,
        Long airId,
        Long ticketId
){

}
