package org.third.thirdseminar.service;


import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.third.thirdseminar.domain.Ticket;
import org.third.thirdseminar.controller.dto.response.CardDto;
import org.third.thirdseminar.infrastructure.ReservationJpaRepository;
import org.third.thirdseminar.infrastructure.TicketJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {

	private final ReservationJpaRepository reservationJpaRepository;
	private final TicketJpaRepository ticketJpaRepository;

	public List<CardDto> getCards(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd E");
		NumberFormat priceFormat = NumberFormat.getCurrencyInstance();
		priceFormat.setCurrency(Currency.getInstance("KRW"));
		return reservationJpaRepository.findAllExcludingCity("삿포로").stream().map((reservation)->
			CardDto.of(reservation.getCityName(),
				reservation.getCountry(),
				format.format(reservation.getStartDate()),
				format.format(reservation.getEndDate()),
				reservation.getAir().getAirName(),
				priceFormat.format(providerMinPrice(reservation.getTickets()))+"원")
		).collect(Collectors.toList());
	}

	private Long providerMinPrice(List<Ticket> tickets){
		if (tickets.isEmpty()){
			return 100L;
		}
		List<Ticket> minPriceTicket= ticketJpaRepository.findTicketWithMinPrice(tickets.stream().map(Ticket::getTicketId).collect(
			Collectors.toList()));
		return minPriceTicket.get(0).getPrice();
	}


}
