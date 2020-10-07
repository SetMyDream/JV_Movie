package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.models.MovieSession;
import java.util.List;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> getAll() {
        return MovieSessionDao.super.getAll(MovieSession.class);
    }
}
