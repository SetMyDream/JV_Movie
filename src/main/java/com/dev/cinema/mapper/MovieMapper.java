package com.dev.cinema.mapper;

import com.dev.cinema.dto.MovieRequestDto;
import com.dev.cinema.dto.MovieResponseDto;
import com.dev.cinema.models.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie fromRequestDto(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }

    public MovieResponseDto toResponseDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }
}
