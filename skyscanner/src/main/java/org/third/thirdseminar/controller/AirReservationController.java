package org.third.thirdseminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.third.thirdseminar.common.ApiResponse;
import org.third.thirdseminar.controller.dto.request.AirReservationRequest;
import org.third.thirdseminar.controller.dto.request.CreateReservationRequest;
import org.third.thirdseminar.controller.dto.response.AirReservationResponse;
import org.third.thirdseminar.controller.dto.response.CreateReservationResponse;
import org.third.thirdseminar.exception.Success;
import org.third.thirdseminar.service.ReservationService;

@RestController
@RequestMapping("/air")
@RequiredArgsConstructor
public class AirReservationController {
    private final ReservationService reservationServcie;

    @GetMapping("/reservations")
    public ApiResponse<AirReservationResponse> getReservertaions(@RequestBody AirReservationRequest request){
        return ApiResponse.success(Success.GET_RESERVATION_SUCCESS,reservationServcie.getReservations(request));
    }

    @PostMapping("/reservation")
    public ApiResponse<CreateReservationResponse> createReservertaion(@RequestBody CreateReservationRequest request){
        return ApiResponse.success(Success.CREATE_RESERVATION_SUCCESS,reservationServcie.createReservation(request));
    }
}
