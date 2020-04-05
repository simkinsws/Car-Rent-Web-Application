package com.car.rentservice.modal;

import com.car.rentservice.audited.Auditable;
import com.car.rentservice.common.converters.MapConverter;
import com.car.rentservice.common.converters.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "car")
@Audited
@Getter
@SuperBuilder
@NoArgsConstructor
public class Car extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serialNumber;
    private String make;
    private String modal;
    private String year;
    private String engine;
    private String fuel;
    private String gear;
    private String wheelsDrive;
    private int doors;
    private int seats;
    private BigDecimal fuelConsumption;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> features;

    private String carClass;
    private BigDecimal pricePerDay;
    private int distanceIncluded;

    @Column(columnDefinition = "TEXT")
    private String about;

    @OneToOne
    private PickUpPlace pickUpPlace;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
