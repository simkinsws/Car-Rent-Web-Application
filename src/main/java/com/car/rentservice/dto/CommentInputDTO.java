package com.car.rentservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Created By Shameera.A on 4/4/2020
 */
@Getter
public class CommentInputDTO {

    private final String post;
    private final long userId;

    @JsonCreator
    public CommentInputDTO(@JsonProperty("post") String post, @JsonProperty("userId")long userId) {
        this.post = post;
        this.userId = userId;
    }
}
