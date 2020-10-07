package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.models.CinemaHall;
import java.util.List;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public List<CinemaHall> getAll() {
        return getAll(CinemaHall.class);
    }
}
