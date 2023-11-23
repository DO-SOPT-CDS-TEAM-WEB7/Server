package org.third.thirdseminar.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.third.thirdseminar.controller.dto.request.TicketRequestDto;
import org.third.thirdseminar.controller.dto.response.DateDto;
import org.third.thirdseminar.controller.dto.response.TicketDto;
import org.third.thirdseminar.domain.Air;
import org.third.thirdseminar.controller.dto.response.AirResultDto;
import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.exception.Error;
import org.third.thirdseminar.exception.model.NotFoundException;
import org.third.thirdseminar.infrastructure.AirJpaRepository;
import org.third.thirdseminar.infrastructure.ReservationJpaRepository;
import org.third.thirdseminar.infrastructure.TicketJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {
	private final ReservationJpaRepository reservationJpaRepository;
	private final AirJpaRepository airJpaRepository;
	private final TicketJpaRepository ticketJpaRepository;
	private final DecimalFormat df = new DecimalFormat("###,###");


	public List<TicketDto> getTicketList(){
		return ticketJpaRepository.findAll().stream().map(
			(ticket)->
				TicketDto.of(
					ticket.getTicketId(),
					ticket.getCompanyName(),
					ticket.getStars(),
					ticket.getCard(),
					df.format(ticket.getPrice()),
					ticket.getComment())).collect(Collectors.toList());
	}

	public AirResultDto getAirInformation(TicketRequestDto ticketRequestDto){
		Air air= airJpaRepository.findById(ticketRequestDto.airId())
			.orElseThrow(()-> new NotFoundException(Error.AIR_NOT_FOUND,Error.AIR_NOT_FOUND.getMessage()));	//air 객체 주워오기
		DateDto dateDto = new DateDto(ticketRequestDto.startDate(), ticketRequestDto.endDate());	//date 객체 주워오기
		Reservation reservation = reservationJpaRepository.findByAir_AirId(air.getAirId());
		return AirResultDto.of(
			dateDto,
			air.getAirId(),
			air.getAirName(),
			reservation.getStartTime(),
			reservation.getEndTime()
		);
	}




}
