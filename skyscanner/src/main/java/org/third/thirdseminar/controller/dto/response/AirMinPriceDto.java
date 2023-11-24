package org.third.thirdseminar.controller.dto.response;

import java.text.DecimalFormat;

public record AirMinPriceDto (
        Long airId,
        String airName,
        String minPriceString
){
    public static AirMinPriceDto of(Long airId, String airName, String minPriceString) {
        return new AirMinPriceDto(airId, airName, minPriceString);
    }

}
