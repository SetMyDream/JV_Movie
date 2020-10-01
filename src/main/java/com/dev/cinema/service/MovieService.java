package com.dev.cinema.service;

import com.dev.cinema.models.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
