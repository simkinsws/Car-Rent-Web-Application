package com.car.rentservice.modal.tags;//package com.car.rentservice.modal.tags;
//
//import com.car.rentservice.audited.Auditable;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.SuperBuilder;
//import org.hibernate.envers.Audited;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "transmission_tags", uniqueConstraints = @UniqueConstraint(columnNames = { "transmission" }))
//@Audited
//@SuperBuilder
//@Getter
//@Setter
//@NoArgsConstructor
//public class TransmissionTag extends Auditable<String> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sequence")
//    @SequenceGenerator(name = "transmissiontagsSequence", sequenceName = "TRANSMISSIONTAGS_SEQ", allocationSize = 1)
//    private Long id;
//
//    @Column(unique = true)
//    private String transmission;
//}
