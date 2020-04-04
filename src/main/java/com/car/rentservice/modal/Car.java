package com.car.rentservice.modal;

import com.car.rentservice.audited.Auditable;
import com.car.rentservice.common.converters.MapConverter;
import com.car.rentservice.common.converters.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created By Shameera.A on 3/28/2020
 */
@Entity
@Table(name = "car")
@Audited
@Getter
@SuperBuilder
@NoArgsConstructor
public class Car extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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

    @Convert(converter = MapConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, String> pickUpPlace;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
