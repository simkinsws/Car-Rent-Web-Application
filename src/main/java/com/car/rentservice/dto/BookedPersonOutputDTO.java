package com.car.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Shameera.A on 4/2/2020
 */

@Getter
@AllArgsConstructor
public class BookedPersonOutputDTO {

    private final String email;
    private final String firstName;
    private final String secondName;
    private final String phone;

}
