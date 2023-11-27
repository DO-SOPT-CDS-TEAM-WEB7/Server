package org.third.thirdseminar.controller.dto.response;
import org.third.thirdseminar.domain.TimeRange;

public record AirResultDto(DateDto dateDto, Long reservationId, String airName, TimeRangeDto startTime, TimeRangeDto endTime) {
	public static AirResultDto of(DateDto dateDto, Long reservationId, String airName, TimeRangeDto startTime, TimeRangeDto endTime) {
		return new AirResultDto(dateDto, reservationId, airName, startTime, endTime);
	}

}
