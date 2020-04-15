package com.car.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReservationOutputDTO {

    private final String orderNumber;
    private final String confirmationCode;
    private final BigDecimal amount;
    private final LocalDateTime bookingDate;
}
