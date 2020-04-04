package com.car.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Shameera.A on 4/2/2020
 */

@Getter
@AllArgsConstructor
public class BookedCarsOutputDTO {

    private final String serialNumber;
    private final BookedPeriodDTO bookedPeriodDTO;
}
