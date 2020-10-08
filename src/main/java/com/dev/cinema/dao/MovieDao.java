package com.dev.cinema.dao;

import com.dev.cinema.models.Movie;
import java.util.List;

public interface MovieDao extends GenericDao<Movie> {
    List<Movie> getAll();
}
