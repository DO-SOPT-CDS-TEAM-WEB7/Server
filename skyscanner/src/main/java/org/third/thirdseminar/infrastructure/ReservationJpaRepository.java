package org.third.thirdseminar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.third.thirdseminar.domain.Reservation;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation,Long> {

}
