package com.dev.cinema.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @Min(value = 10, message = "All halls' capacities are greater than 10")
    int capacity;
    @NotNull(message = "CinemaHall has to have a description")
    @Size(min = 5, message = "Description is incomplete")
    String description;
}
