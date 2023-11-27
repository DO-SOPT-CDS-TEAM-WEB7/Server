package org.third.thirdseminar.controller.dto.response;

import java.util.List;

public record TicketPageDto(AirResultDto airResultDto, List<TicketDto> ticketListDto) {

	public static TicketPageDto of (AirResultDto airResultDto, List<TicketDto> ticketListDto){
		return new TicketPageDto(airResultDto,ticketListDto);
	}
}
