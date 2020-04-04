package com.car.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created By Shameera.A on 4/2/2020
 */
@Getter
@AllArgsConstructor
public class CommentsOutputDTO {

    private final String firstName;
    private final String secondName;
    private final String photoUrl;
    private final LocalDateTime postDate;
    private final String post;
}
