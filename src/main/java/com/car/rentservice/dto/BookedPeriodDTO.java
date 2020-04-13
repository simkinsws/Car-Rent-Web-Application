package com.car.rentservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookedPeriodDTO {

    private final String orderNumber;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final boolean paid;
    private final BigDecimal amount;
    private final LocalDateTime bookingDate;
}
