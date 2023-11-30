package org.third.thirdseminar.service;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.third.thirdseminar.controller.dto.request.TicketRequestDto;
import org.third.thirdseminar.controller.dto.response.DateDto;
import org.third.thirdseminar.controller.dto.response.TicketDto;
import org.third.thirdseminar.controller.dto.response.TimeRangeDto;
import org.third.thirdseminar.domain.Air;
import org.third.thirdseminar.controller.dto.response.AirResultDto;
import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.domain.TimeRange;
import org.third.thirdseminar.exception.Error;
import org.third.thirdseminar.exception.model.NotFoundException;
import org.third.thirdseminar.infrastructure.AirJpaRepository;
import org.third.thirdseminar.infrastructure.ReservationJpaRepository;
import org.third.thirdseminar.infrastructure.TicketJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

	public AirResultDto getAirInformation(Date startDate, Date endDate, Long reservationId){
		Reservation reservation = reservationJpaRepository.findById(reservationId)
			.orElseThrow( ()-> new NotFoundException(Error.RESERVATION_NOT_FOUND, Error.RESERVATION_NOT_FOUND.getMessage())
		);
		Air air= reservation.getAir();
		DateDto dateDto = new DateDto(startDate, endDate);	//date 객체 주워오기

		return AirResultDto.of(
			dateDto,
			reservation.getId(),
			air.getAirName(),
			TimeRangeFormat(reservation.getStartTime()),
			TimeRangeFormat(reservation.getEndTime())
		);
	}

	private TimeRangeDto TimeRangeFormat(TimeRange timeRange){
		String startTime = timeRange.getStart().format( DateTimeFormatter.ofPattern( "HH:mm" ));
		String endTime = timeRange.getEnd().format( DateTimeFormatter.ofPattern( "HH:mm" ));
		String during = timeRange.getDuring().format(DateTimeFormatter.ofPattern("H시간 mm분"));


		return new TimeRangeDto(startTime, endTime, during);
	}




}
