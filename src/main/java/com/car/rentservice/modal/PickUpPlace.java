package com.car.rentservice.modal;

import com.car.rentservice.audited.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "pickup_place")
@Audited
@Getter
@SuperBuilder
@NoArgsConstructor
public class PickUpPlace extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String placeId;
    private String placeName;
    private BigDecimal latitude;
    private BigDecimal longitude;


}
