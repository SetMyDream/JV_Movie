package com.dev.cinema.dao;

import com.dev.cinema.models.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao extends GenericDao<MovieSession> {
    List<MovieSession> getAll();

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
