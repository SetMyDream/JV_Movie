package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.models.CinemaHall;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImplImpl extends GenericDaoImpl<CinemaHall> implements CinemaHallDao {
    public CinemaHallDaoImplImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<CinemaHall> getAll() {
        return getAll(CinemaHall.class);
    }
}
