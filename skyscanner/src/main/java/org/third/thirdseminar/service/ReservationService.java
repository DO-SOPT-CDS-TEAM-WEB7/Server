package org.third.thirdseminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.third.thirdseminar.controller.dto.reqeust.AirReservationReqeust;
import org.third.thirdseminar.controller.dto.reqeust.CreateReservationRequest;
import org.third.thirdseminar.controller.dto.response.*;
import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.domain.Ticket;
import org.third.thirdseminar.domain.TimeRange;
import org.third.thirdseminar.infrastructure.ReservationJpaRepository;
import org.third.thirdseminar.infrastructure.TickectJpaRepository;

import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationJpaRepository reservationJpaRepository;
    private final TickectJpaRepository tickectJpaRepository;
    private final DecimalFormat df = new DecimalFormat("###,###");

    public AirReservationResponse getReservations(AirReservationReqeust reqeust){
        List<Reservation> reservations = reservationJpaRepository.findAll();

        List<AirDto> airList = new ArrayList<>();
        for(Reservation reservation : reservations){
            List<Ticket> ticket= tickectJpaRepository.findByReservationIdOrderByPriceAsc(reservation.getId());
            airList.add(AirDto.of(reservation, df.format(ticket.get(0).getPrice()), TimeRangeFormat(reservation.getStartTime()), TimeRangeFormat(reservation.getEndTime())));
        }
        DateDto dateDto = new DateDto(reqeust.startDate(), reqeust.endDate());
        return new AirReservationResponse(dateDto, airList);
    }

    public CreateReservationResponse createReservation(CreateReservationRequest request){
        Optional<Ticket> ticket = tickectJpaRepository.findById(request.ticketId());
        Reservation reservation = ticket.get().getReservation();

        return CreateReservationResponse.of(reservation.getStartTime().getStart(), reservation.getEndTime().getEnd(), String.format("￦%,d", ticket.get().getPrice()));

    }

    public TimeRangeDto TimeRangeFormat(TimeRange timeRange){
        String startTime = timeRange.getStart().format( DateTimeFormatter.ofPattern( "HH:mm" ));
        String endTime = timeRange.getEnd().format( DateTimeFormatter.ofPattern( "HH:mm" ));
        String during = timeRange.getDuring().format(DateTimeFormatter.ofPattern("HH시간 mm분"));

        return new TimeRangeDto(startTime, endTime, during);
    }
}
