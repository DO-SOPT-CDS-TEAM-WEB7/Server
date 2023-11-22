package org.third.thirdseminar.controller.dto.response;


public record TimeRangeDto (
        String startTime,
        String endTime,
        String during){
    public static TimeRangeDto of(String startTime, String endTime, String during) {
        return new TimeRangeDto(
                startTime, endTime, during
        );
    }
}
