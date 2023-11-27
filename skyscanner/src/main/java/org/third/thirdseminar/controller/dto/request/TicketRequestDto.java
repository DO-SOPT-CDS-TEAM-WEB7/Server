package org.third.thirdseminar.controller.dto.request;

import java.util.Date;

public record TicketRequestDto(Date startDate, Date endDate, Long reservationId) {
	public static TicketRequestDto of(Date startDate, Date endDate, Long reservationId){
		return new TicketRequestDto(startDate,endDate,reservationId);
	}
}
