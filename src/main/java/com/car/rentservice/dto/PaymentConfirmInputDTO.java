package com.car.rentservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Created By Shameera.A on 4/4/2020
 */

@Getter
public class PaymentConfirmInputDTO {

    private final String orderNumber;
    private final String confirmationCode;

    @JsonCreator
    public PaymentConfirmInputDTO(@JsonProperty("orderNumber") String orderNumber,
                                  @JsonProperty("confirmationCode") String confirmationCode) {
        this.orderNumber = orderNumber;
        this.confirmationCode = confirmationCode;
    }
}
