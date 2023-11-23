package org.third.thirdseminar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.third.thirdseminar.domain.Reservation;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
	@Query("SELECT r FROM Reservation r WHERE r.cityName <> :cityName")
	List<Reservation> findAllExcludingCity(@Param("cityName") String cityName);

	Reservation findByAir_AirId(@Param("airId") Long airId);
}
