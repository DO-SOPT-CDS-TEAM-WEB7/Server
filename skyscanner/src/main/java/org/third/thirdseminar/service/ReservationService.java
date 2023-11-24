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
import org.third.thirdseminar.controller.dto.request.CreateReservationRequest;
import org.third.thirdseminar.controller.dto.response.*;
import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.domain.ReservationResult;
import org.third.thirdseminar.domain.Ticket;
import org.third.thirdseminar.domain.TimeRange;
import org.third.thirdseminar.infrastructure.ReservationJpaRepository;
import org.third.thirdseminar.infrastructure.ReservationResultJpaRepository;
import org.third.thirdseminar.infrastructure.TicketJpaRepository;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationJpaRepository reservationJpaRepository;
    private final TicketJpaRepository tickectJpaRepository;
    private final ReservationResultJpaRepository reservationResultJpaRepository;
    private final DecimalFormat df = new DecimalFormat("###,###");

    public AirReservationResponse getReservations(AirReservationRequest reqeust){
        List<Reservation> reservations = reservationJpaRepository.findAll();

        List<AirDto> airList = new ArrayList<>();
        for(Reservation reservation : reservations){
            List<Ticket> ticket= tickectJpaRepository.findByReservationIdOrderByPriceAsc(reservation.getId());
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


        String startDateTime = reservationDateFormat(reservation.getStartDate(), reservation.getStartTime().getStart());
        String endDateTime = reservationDateFormat(reservation.getEndDate(), reservation.getEndTime().getStart());

        return CreateReservationResponse.of(startDateTime, endDateTime, ticket.getCompanyName(),String.format("￦%,d", ticket.getPrice()));

    }

    public AirMinPriceResponse getAirMinPrices(){
        List<AirMinPriceDto> airMinPrices = tickectJpaRepository.findMinPricesByAirId().stream().map(
                row -> AirMinPriceDto.of(
                        (Long) row[0],        // airId
                        (String) row[1],      // airName
                        df.format(row[2])+"원부터" // minPriceString
                )).collect(Collectors.toList());

        return new AirMinPriceResponse(airMinPrices);

    }


    private TimeRangeDto TimeRangeFormat(TimeRange timeRange){
        String startTime = timeRange.getStart().format( DateTimeFormatter.ofPattern( "H:mm" ));
        String endTime = timeRange.getEnd().format( DateTimeFormatter.ofPattern( "H:mm" ));
        String during = timeRange.getDuring().format(DateTimeFormatter.ofPattern("H시간 mm분"));

        return new TimeRangeDto(startTime, endTime, during);
    }

    private String reservationDateFormat(Date date, LocalTime time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(date) + " " + time.format(DateTimeFormatter.ofPattern("a H:mm"));
    }
}
