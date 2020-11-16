package com.dev.cinema.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequestDto {
    @NotNull(message = "Movie has to have a title")
    private String title;
    @NotNull(message = "Movie has to have a description")
    @Size(min = 5, message = "Description is incomplete")
    private String description;
}
