package org.third.thirdseminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.third.thirdseminar.controller.dto.request.AirReservationRequest;
import org.third.thirdseminar.controller.dto.response.AirDto;
import org.third.thirdseminar.controller.dto.response.AirReservationResponse;
import org.third.thirdseminar.controller.dto.response.DateDto;
import org.third.thirdseminar.controller.dto.response.TimeRangeDto;
import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.domain.Ticket;
import org.third.thirdseminar.domain.TimeRange;
import org.third.thirdseminar.infrastructure.ReservationJpaRepository;
import org.third.thirdseminar.infrastructure.TicketJpaRepository;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationJpaRepository reservationJpaRepository;
    private final TicketJpaRepository ticketJpaRepository;
    private final DecimalFormat df = new DecimalFormat("###,###");

    public AirReservationResponse getReservations(AirReservationRequest reqeust){
        List<Reservation> reservations = reservationJpaRepository.findAll();

        List<AirDto> airList = new ArrayList<>();
        for(Reservation reservation : reservations){
            List<Ticket> ticket= ticketJpaRepository.findByReservationIdOrderByPriceAsc(reservation.getId());
            airList.add(AirDto.of(reservation, df.format(ticket.get(0).getPrice()), TimeRangeFormat(reservation.getStartTime()), TimeRangeFormat(reservation.getEndTime())));
        }
        DateDto dateDto = new DateDto(reqeust.startDate(), reqeust.endDate());
        return new AirReservationResponse(dateDto, airList);
    }

    private TimeRangeDto TimeRangeFormat(TimeRange timeRange){
        String startTime = timeRange.getStart().format( DateTimeFormatter.ofPattern( "HH:mm" ));
        String endTime = timeRange.getEnd().format( DateTimeFormatter.ofPattern( "HH:mm" ));
        String during = timeRange.getDuring().format(DateTimeFormatter.ofPattern("HH시간 mm분"));

        return new TimeRangeDto(startTime, endTime, during);
    }
}
