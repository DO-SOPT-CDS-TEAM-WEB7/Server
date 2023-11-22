package org.third.thirdseminar.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Air {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="air_id")
    private Long airId;
    private String airName;
    private int CO2;

}
