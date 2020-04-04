package com.car.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created By Shameera.A on 4/1/2020
 */
@Getter
@AllArgsConstructor
public class BookedPeriodsDTO {

    private final String orderNumber;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final boolean paid;
    private final BigDecimal amount;
    private final LocalDateTime bookingDate;
    private final BookedPersonOutputDTO bookedPerson;
}
