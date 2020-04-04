package com.car.rentservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationInputDTO {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final BookedPersonOutputDTO bookedPerson;

    @JsonCreator
    public ReservationInputDTO(@JsonProperty("startDateTime")LocalDateTime startDateTime, @JsonProperty("endDateTime")LocalDateTime endDateTime,
                               @JsonProperty("bookedPerson")BookedPersonOutputDTO bookedPerson) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.bookedPerson = bookedPerson;
    }
}
