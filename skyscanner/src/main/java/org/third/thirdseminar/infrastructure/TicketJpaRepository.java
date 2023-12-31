package org.third.thirdseminar.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.third.thirdseminar.domain.Ticket;


public interface TicketJpaRepository extends JpaRepository<Ticket, Long> {
	@Query("SELECT t FROM Ticket t WHERE t.ticketId IN :ticketIds AND t.price = (SELECT MIN(tt.price) FROM Ticket tt WHERE tt.ticketId IN :ticketIds)")
	List<Ticket> findTicketWithMinPrice(@Param("ticketIds") List<Long> ticketIds);

	List<Ticket> findByReservationIdOrderByPriceAsc(Long airId);

	@Query("SELECT r.air.airId, r.air.airName, MIN(t.price) FROM Ticket t JOIN Reservation r ON t.ticketId = r.id  GROUP BY r.air.airId, r.air.airName ORDER BY r.air.airName")
	List<Object[]> findMinPricesByAirId();



}
