package org.third.thirdseminar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.third.thirdseminar.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
	@Query("SELECT r FROM Reservation r WHERE r.cityName <> :cityName")
	List<Reservation> findAllExcludingCity(@Param("cityName") String cityName);

	Optional<Reservation> findById(Long reservationId);
}
