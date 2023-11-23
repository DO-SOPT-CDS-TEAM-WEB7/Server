package org.third.thirdseminar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.third.thirdseminar.common.ApiResponse;
import org.third.thirdseminar.controller.dto.request.TicketRequestDto;
import org.third.thirdseminar.controller.dto.response.TicketPageDto;
import org.third.thirdseminar.exception.Success;
import org.third.thirdseminar.service.TicketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TicketController {

	private final TicketService ticketService;

	@GetMapping("/ticket")
	public ApiResponse<TicketPageDto> getTicket(@RequestBody TicketRequestDto ticketRequestDto){
		return ApiResponse.success(Success.GET_TICKET_SUCCESS, TicketPageDto.of(
			ticketService.getAirInformation(ticketRequestDto),
			ticketService.getTicketList())
		);
	}
}
