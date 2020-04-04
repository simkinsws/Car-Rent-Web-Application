package com.car.rentservice.dto;

import com.car.rentservice.modal.Car;
import com.car.rentservice.modal.Comments;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created By Shameera.A on 4/1/2020
 */

@Getter
@AllArgsConstructor
public class UserSuccessResponseDTO {

    private final String firstName;
    private final String secondName;
    private final LocalDateTime registrationDate;
    private final List<CommentsOutputDTO> comments;
    private final List<CarOutputDTO> ownCars;
    private final List<BookedCarsOutputDTO> bookedCars;
    private final List<BookedCarsOutputDTO> history;
}
