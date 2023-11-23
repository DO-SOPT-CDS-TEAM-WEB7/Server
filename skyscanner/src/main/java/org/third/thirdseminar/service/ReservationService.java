package org.third.thirdseminar.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.third.thirdseminar.controller.dto.request.AirReservationRequest;
import org.third.thirdseminar.controller.dto.response.AirDto;
import org.third.thirdseminar.controller.dto.response.AirReservationResponse;
import org.third.thirdseminar.controller.dto.response.DateDto;
import org.third.thirdseminar.controller.dto.response.TimeRangeDto;
import org.third.thirdseminar.controller.dto.reqeust.AirReservationReqeust;
import org.third.thirdseminar.controller.dto.reqeust.CreateReservationRequest;
import org.third.thirdseminar.controller.dto.response.*;
import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.domain.ReservationResult;
import org.third.thirdseminar.domain.Ticket;
import org.third.thirdseminar.domain.TimeRange;
import org.third.thirdseminar.infrastructure.ReservationJpaRepository;
import org.third.thirdseminar.infrastructure.TicketJpaRepository;
import org.third.thirdseminar.infrastructure.ReservationResultJpaRepository;
import org.third.thirdseminar.infrastructure.TickectJpaRepository;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationJpaRepository reservationJpaRepository;
    private final TickectJpaRepository tickectJpaRepository;
    private final ReservationResultJpaRepository reservationResultJpaRepository;
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

    @Transactional
    public CreateReservationResponse createReservation(CreateReservationRequest request){
        Ticket ticket = tickectJpaRepository.findById(request.ticketId()).orElseThrow(()-> new EntityNotFoundException("없는 항공편입니다."));
        Reservation reservation = ticket.getReservation();

        ReservationResult reservationResult = reservationResultJpaRepository.save(
                ReservationResult.builder()
                        .reservation(reservation)
                        .ticket(ticket).build());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateTime = simpleDateFormat.format(reservation.getStartDate()) + " "+ reservation.getStartTime().getStart().format(DateTimeFormatter.ofPattern( "a H:mm" ));
        String endDateTime = simpleDateFormat.format(reservation.getEndDate()) + " " + reservation.getEndTime().getStart().format(DateTimeFormatter.ofPattern( "a H:mm" ));

        return CreateReservationResponse.of(startDateTime, endDateTime, ticket.getCompanyName(),String.format("￦%,d", ticket.getPrice()));

    }

    private TimeRangeDto TimeRangeFormat(TimeRange timeRange){
        String startTime = timeRange.getStart().format( DateTimeFormatter.ofPattern( "H:mm" ));
        String endTime = timeRange.getEnd().format( DateTimeFormatter.ofPattern( "H:mm" ));
        String during = timeRange.getDuring().format(DateTimeFormatter.ofPattern("H시간 mm분"));

        return new TimeRangeDto(startTime, endTime, during);
    }
}
