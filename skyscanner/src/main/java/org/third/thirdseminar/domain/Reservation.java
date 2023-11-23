package org.third.thirdseminar.domain;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private Long id;
    private Date startDate;
    private Date endDate;
    private String country;
    private String cityName;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "start_time_first", insertable = false, updatable = false)),
            @AttributeOverride(name = "during", column = @Column(name = "during_time_first", insertable = false, updatable = false)),
            @AttributeOverride(name = "end", column = @Column(name = "end_time_first", insertable = false, updatable = false))
    })
    private TimeRange startTime;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "start_time_end", insertable = false, updatable = false)),
            @AttributeOverride(name = "during", column = @Column(name = "during_time_end", insertable = false, updatable = false)),
            @AttributeOverride(name = "end", column = @Column(name = "end_time_end", insertable = false, updatable = false))
    })
    private TimeRange endTime;

    @ManyToOne
    @JoinColumn(name = "air_id")
    private Air air;

}
