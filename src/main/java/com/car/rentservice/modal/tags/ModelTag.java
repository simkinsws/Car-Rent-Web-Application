package com.car.rentservice.modal.tags;

import com.car.rentservice.audited.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "model_tags", uniqueConstraints = @UniqueConstraint(columnNames = { "model" }))
@Audited
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class ModelTag extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modeltagSequence")
    @SequenceGenerator(name = "modeltagSequence", sequenceName = "MODELTAGS_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String model;
}
