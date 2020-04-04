package com.car.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created By Shameera.A on 4/1/2020
 */
@Getter
@AllArgsConstructor
public class OwnerOutputDTO {

    private final String firstName;
    private final String secondName;
    private final LocalDateTime registrationDate;
}
