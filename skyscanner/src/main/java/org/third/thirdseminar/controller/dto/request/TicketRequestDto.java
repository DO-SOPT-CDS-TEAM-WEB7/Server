package org.third.thirdseminar.controller.dto.request;

import java.util.Date;

public record TicketRequestDto(Date startDate, Date endDate, Long airId) {
	public static TicketRequestDto of(Date startDate, Date endDate, Long airId){
		return new TicketRequestDto(startDate,endDate,airId);
	}
}
