package com.car.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReservationOutputDTO {

    private final String orderNumber;
    private final BigDecimal amount;
    private final LocalDateTime bookingDate;
}
