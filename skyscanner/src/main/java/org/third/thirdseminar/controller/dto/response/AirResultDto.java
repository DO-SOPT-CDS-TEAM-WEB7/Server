package org.third.thirdseminar.controller.dto.response;
import org.third.thirdseminar.domain.TimeRange;

public record AirResultDto(DateDto dateDto, Long airId, String airName, TimeRange startTime, TimeRange endTime) {
	public static AirResultDto of(DateDto dateDto, Long airId, String airName, TimeRange startTime, TimeRange endTime) {
		return new AirResultDto(dateDto, airId, airName, startTime, endTime);
	}

}
