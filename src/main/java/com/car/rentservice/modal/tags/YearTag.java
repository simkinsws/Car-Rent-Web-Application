package com.car.rentservice.modal.tags;

import com.car.rentservice.audited.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "year_tags", uniqueConstraints = @UniqueConstraint(columnNames = { "year" }))
@Audited
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class YearTag extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "yeartagSequence")
    @SequenceGenerator(name = "yeartagSequence", sequenceName = "YEARTAG_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String year;
}
