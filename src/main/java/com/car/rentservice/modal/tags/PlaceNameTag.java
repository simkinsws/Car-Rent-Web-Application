package com.car.rentservice.modal.tags;

import com.car.rentservice.audited.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "placename_tags", uniqueConstraints = @UniqueConstraint(columnNames = { "city" }))
@Audited
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class PlaceNameTag extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placenametagsSequence")
    @SequenceGenerator(name = "placenametagsSequence", sequenceName = "PLACENAMETAGS_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String city;
}
