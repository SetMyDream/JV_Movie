package com.dev.cinema.dto;

import lombok.Data;

@Data
public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
    private String sessionDateTime;
}
