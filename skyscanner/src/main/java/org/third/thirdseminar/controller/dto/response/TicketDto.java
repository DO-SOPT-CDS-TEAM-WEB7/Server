package org.third.thirdseminar.controller.dto.response;

public record TicketDto(Long ticketId, String companyName, int stars, String card, String price, int comment) {
	public static TicketDto of(Long ticketId, String companyName, int stars, String card, String price, int comment){
		return new TicketDto(ticketId, companyName, stars, card, price, comment);
	}
}
