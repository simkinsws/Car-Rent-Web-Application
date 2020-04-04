package com.car.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookedCarsOutputDTO {

    private final String serialNumber;
    private final List<BookedPeriodDTO> bookedPeriodDTO;
}
