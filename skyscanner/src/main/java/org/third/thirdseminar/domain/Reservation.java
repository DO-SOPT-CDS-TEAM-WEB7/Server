package org.third.thirdseminar.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reservation {
	@Id @GeneratedValue
	@Column(name="reservation_id")
	private Long id;
	private Date startDate;
	private Date endDate;
	private String country;
	private String cityName;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "start", column = @Column(name = "start_time_first", insertable = false, updatable = false)),
		@AttributeOverride(name = "during", column = @Column(name = "during_time_first", insertable = false, updatable = false)),
		@AttributeOverride(name = "end", column = @Column(name = "end_time_first", insertable = false, updatable = false))
	})
	private TimeRange startTime;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "start", column = @Column(name = "start_time_end", insertable = false, updatable = false)),
		@AttributeOverride(name = "during", column = @Column(name = "during_time_end", insertable = false, updatable = false)),
		@AttributeOverride(name = "end", column = @Column(name = "end_time_end", insertable = false, updatable = false))
	})
	private TimeRange endTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
	private List<Ticket> tickets = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "air_id")
	private Air air;


}
