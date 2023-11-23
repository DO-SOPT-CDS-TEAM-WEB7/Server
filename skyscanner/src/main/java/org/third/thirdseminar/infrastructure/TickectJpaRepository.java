package org.third.thirdseminar.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.third.thirdseminar.domain.Reservation;
import org.third.thirdseminar.domain.Ticket;

import java.util.List;

public interface TickectJpaRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByReservationIdOrderByPriceAsc(Long airId);
}
