package org.third.thirdseminar.domain;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TimeRange {
	private LocalTime start;
	private LocalTime during;
	private LocalTime end;
}
