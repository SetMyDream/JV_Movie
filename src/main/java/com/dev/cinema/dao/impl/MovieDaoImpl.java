package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.models.Movie;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends GenericDaoImpl<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Movie> getAll() {
        return getAll(Movie.class);
    }

    @Override
    public Movie get(Long movieId) {
        return get(Movie.class, movieId);
    }
}
