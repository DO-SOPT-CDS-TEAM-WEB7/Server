package org.third.thirdseminar.controller.dto.response;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class DateDto {
	private final String startDate;
	private final String endDate;

	public DateDto(Date startDate, Date endDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E");
		this.startDate = simpleDateFormat.format(startDate);
		this.endDate = simpleDateFormat.format(endDate);
	}
}