package org.third.thirdseminar.controller.dto.response;

public record CreateReservationResponse(
        String startDateTime,
        String endDateTime,
        String companyName,
        String price

){
    public static CreateReservationResponse of(String startDateTime,
                                               String endDateTime,
                                               String companyName,
                                               String price){

        return new CreateReservationResponse(startDateTime,endDateTime, companyName, price);
    }
}
