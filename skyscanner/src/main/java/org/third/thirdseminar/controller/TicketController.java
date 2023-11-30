package org.third.thirdseminar.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.third.thirdseminar.common.ApiResponse;
import org.third.thirdseminar.controller.dto.response.TicketPageDto;
import org.third.thirdseminar.exception.Success;
import org.third.thirdseminar.service.TicketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TicketController {

	private final TicketService ticketService;

	@GetMapping("/ticket")
	public ApiResponse<TicketPageDto> getTicket(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate, @RequestParam Long reservationId){
		return ApiResponse.success(Success.GET_TICKET_SUCCESS, TicketPageDto.of(
			ticketService.getAirInformation(startDate,endDate,reservationId),
			ticketService.getTicketList())
		);
	}
}
