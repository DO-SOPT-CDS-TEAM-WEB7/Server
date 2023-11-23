package org.third.thirdseminar.controller.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.third.thirdseminar.common.ApiResponse;
import org.third.thirdseminar.controller.dto.reqeust.AirReservationReqeust;
import org.third.thirdseminar.controller.dto.response.AirReservationResponse;
import org.third.thirdseminar.exception.Success;
import org.third.thirdseminar.service.ReservationService;

@RestController
@RequestMapping("/air")
@RequiredArgsConstructor
public class AirReservationController {
    private final ReservationService reservationServcie;

    @GetMapping("/reservation")
    public ApiResponse<AirReservationResponse> getReservertaions(@RequestBody AirReservationReqeust request){
        return ApiResponse.success(Success.GET_RESERVATION_SUCCESS,reservationServcie.getReservations(request));
    }
}
