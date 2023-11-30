package org.third.thirdseminar.controller;

import java.util.Date;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.third.thirdseminar.common.ApiResponse;
import org.third.thirdseminar.controller.dto.request.AirReservationRequest;
import org.third.thirdseminar.controller.dto.request.CreateReservationRequest;
import org.third.thirdseminar.controller.dto.response.AirMinPriceResponse;
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
    public ApiResponse<AirReservationResponse> getReservertaions(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date endDate){
        return ApiResponse.success(Success.GET_RESERVATION_SUCCESS,reservationServcie.getReservations(startDate,endDate));
    }

    @PostMapping("/reservation")
    public ApiResponse<CreateReservationResponse> createReservertaion(@RequestBody CreateReservationRequest request){
        return ApiResponse.success(Success.CREATE_RESERVATION_SUCCESS,reservationServcie.createReservation(request));
    }

    @GetMapping("/min-prices")
    public ApiResponse<AirMinPriceResponse> getAirMinPrices(){
        return ApiResponse.success(Success.GET_AIR_MINPRICE_SUCESS, reservationServcie.getAirMinPrices());
    }
}
