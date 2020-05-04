package com.car.rentservice.modal.tags;

import com.car.rentservice.audited.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "make_tags", uniqueConstraints = @UniqueConstraint(columnNames = { "make" }))
@Audited
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class MakeTags extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maketagSequence")
    @SequenceGenerator(name = "maketagsSequence", sequenceName = "MAKETAGS_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String make;
}
