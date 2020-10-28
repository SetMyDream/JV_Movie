package com.dev.cinema.dao;

import com.dev.cinema.models.CinemaHall;
import java.util.List;

public interface CinemaHallDao extends GenericDao<CinemaHall> {
    List<CinemaHall> getAll();

    CinemaHall get(Long cinemaHallId);
}
