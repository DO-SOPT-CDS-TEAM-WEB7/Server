package org.third.thirdseminar.controller.dto.response;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Getter
public class DateDto {
    private String startDate;
    private String endDate;

    public DateDto(Date startDate, Date endDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E");
        this.startDate = simpleDateFormat.format(startDate);
        this.endDate = simpleDateFormat.format(endDate);
    }
}
