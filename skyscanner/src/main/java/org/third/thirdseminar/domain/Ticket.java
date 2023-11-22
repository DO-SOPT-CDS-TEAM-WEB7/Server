package org.third.thirdseminar.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private String companyName;

    private int stars;

    private int comment;

    private String card;

    private Long price;

    @ManyToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

}