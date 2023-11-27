package org.third.thirdseminar.controller.dto.response;


public record TimeRangeDto (
        String start,
        String end,
        String during){
    public static TimeRangeDto of(String start, String end, String during) {
        return new TimeRangeDto(
                start, end, during
        );
    }
}
