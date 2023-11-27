package org.third.thirdseminar.domain;


public record ReservationMinPriceDTO (
    Reservation reservation,
    Long minPrice
){
    public static ReservationMinPriceDTO of(Reservation reservation,Long minPrice) {
        return new ReservationMinPriceDTO(reservation, minPrice);
    }

}
