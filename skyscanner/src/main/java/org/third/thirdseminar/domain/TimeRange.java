package org.third.thirdseminar.domain;

import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TimeRange {
	private LocalTime start;
	private LocalTime during;
	private LocalTime end;
}

