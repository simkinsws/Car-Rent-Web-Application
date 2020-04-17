package com.car.rentservice.modal;

import com.car.rentservice.audited.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Audited
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Reservation extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservationSequence")
	@SequenceGenerator(name = "reservationSequence", sequenceName = "RESERVATION_SEQ", allocationSize = 1)
	private Long id;

	private String orderNumber;
	private BigDecimal amount;
	private LocalDateTime bookingDate;
	private String confirmationCode;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	private String serialNumber;

	@Builder.Default
	private boolean paid = false;

}
