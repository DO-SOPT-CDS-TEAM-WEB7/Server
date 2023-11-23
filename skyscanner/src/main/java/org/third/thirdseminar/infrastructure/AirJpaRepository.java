package org.third.thirdseminar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.third.thirdseminar.domain.Air;

public interface AirJpaRepository extends JpaRepository<Air, Long> {
}
