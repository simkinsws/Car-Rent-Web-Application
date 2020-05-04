package com.car.rentservice.modal.tags;

import com.car.rentservice.audited.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "fuel_tag", uniqueConstraints = @UniqueConstraint(columnNames = { "fuel" }))
@Audited
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class FuelTag extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fueltagsSequence")
    @SequenceGenerator(name = "fueltagsSequence", sequenceName = "FUELTAGS_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String fuel;
}
