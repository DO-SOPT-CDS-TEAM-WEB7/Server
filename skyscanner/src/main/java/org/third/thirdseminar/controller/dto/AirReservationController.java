package org.third.thirdseminar.controller.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/reservations")
    public ApiResponse<AirReservationResponse> getReservertaions(@RequestBody AirReservationReqeust request){
        return ApiResponse.success(Success.GET_RESERVATION_SUCCESS,reservationServcie.getReservations(request));
    }

    @PostMapping("/reservation")
    public ApiResponse<CreateReserverationResponse> createReservertaion(@RequestBody CreateReservationReqeust request){
        return ApiResponse.success(Success.CREATE_RESERVATION_SUCCESS,reservationServcie.createReservation(request));
    }
}
