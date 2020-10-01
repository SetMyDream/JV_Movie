package com.dev.cinema.dao;

import com.dev.cinema.models.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
