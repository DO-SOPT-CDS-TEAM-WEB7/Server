package org.third.thirdseminar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.third.thirdseminar.controller.dto.response.AirDto;
import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.domain.ReservationMinPriceDTO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
	@Query("SELECT r FROM Reservation r WHERE r.cityName <> :cityName")
	List<Reservation> findAllExcludingCity(@Param("cityName") String cityName);

	Reservation findByAir_AirId(@Param("airId") Long airId);


	@Query("SELECT r, MIN(t.price) FROM Reservation r JOIN r.tickets t WHERE r.country = :country GROUP BY r ORDER BY MIN(t.price) ASC")
	List<Object[]> findReservations(@Param("country") String country);


}
