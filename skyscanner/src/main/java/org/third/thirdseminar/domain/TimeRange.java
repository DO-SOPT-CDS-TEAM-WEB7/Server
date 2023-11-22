package org.third.thirdseminar.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TimeRange {
    private LocalTime start;
    private LocalTime during;
    private LocalTime end;

}

