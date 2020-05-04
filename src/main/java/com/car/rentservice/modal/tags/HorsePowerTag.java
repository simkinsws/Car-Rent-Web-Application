package com.car.rentservice.modal.tags;

import com.car.rentservice.audited.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "horsepower_tags", uniqueConstraints = @UniqueConstraint(columnNames = { "horsepower" }))
@Audited
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class HorsePowerTag extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "horsepowertagsSequence")
    @SequenceGenerator(name = "horsepowertagsSequence", sequenceName = "HORSEPOWERTAGS_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String horsepower;
}
