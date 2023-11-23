package org.third.thirdseminar.controller.dto.response;

import org.third.thirdseminar.controller.dto.reqeust.AirReservationReqeust;
import org.third.thirdseminar.domain.Reservation;

import java.text.DateFormat;
import java.util.List;

public record AirReservationResponse(
        DateDto dateDto,
        List<AirDto> airDto

){
}