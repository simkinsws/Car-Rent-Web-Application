package com.car.rentservice.modal.tags;

import com.car.rentservice.audited.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "engine_tags", uniqueConstraints = @UniqueConstraint(columnNames = { "engine" }))
@Audited
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class EngineTag extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enginetagsSequence")
    @SequenceGenerator(name = "enginetagsSequence", sequenceName = "ENGINETAGS_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String engine;
}
