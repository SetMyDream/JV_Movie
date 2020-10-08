package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.models.Movie;
import java.util.List;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public List<Movie> getAll() {
        return getAll(Movie.class);
    }
}
