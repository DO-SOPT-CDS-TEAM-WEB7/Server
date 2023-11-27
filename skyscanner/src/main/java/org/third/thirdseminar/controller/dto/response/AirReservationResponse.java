package org.third.thirdseminar.controller.dto.response;

import java.util.List;

public record AirReservationResponse(
        DateDto dateDto,
        List<ReservationDto> reservationListDto

){
}