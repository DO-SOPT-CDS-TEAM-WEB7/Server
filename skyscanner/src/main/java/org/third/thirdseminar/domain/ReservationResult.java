package org.third.thirdseminar.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReservationResult extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationResultId;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket tickId;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservationId;

    @Builder
    public ReservationResult(Ticket ticket, Reservation reservation){
        this.tickId = ticket;
        this.reservationId = reservation;
    }

}
