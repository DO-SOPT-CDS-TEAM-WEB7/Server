package org.third.thirdseminar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.third.thirdseminar.domain.ReservationResult;

public interface ReservationResultJpaRepository extends JpaRepository<ReservationResult,Long> {
}
