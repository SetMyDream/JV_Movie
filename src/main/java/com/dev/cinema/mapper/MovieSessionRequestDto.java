package com.dev.cinema.mapper;

import lombok.Data;

@Data
public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
    private String sessionDateTime;
}
