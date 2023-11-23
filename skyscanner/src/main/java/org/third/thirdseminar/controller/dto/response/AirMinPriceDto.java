package org.third.thirdseminar.controller.dto.response;

import java.text.DecimalFormat;

public record AirMinPriceDto (
        Long airId,
        String airName,
        String minPriceString
){
    public static AirMinPriceDto of(Long airId, String airName, Long minPriceString) {
        DecimalFormat df = new DecimalFormat("###,###원부터");
        return new AirMinPriceDto(airId, airName, df.format(minPriceString));
    }

}
