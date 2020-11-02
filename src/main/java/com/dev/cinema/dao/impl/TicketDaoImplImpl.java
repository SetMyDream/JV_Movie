package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.models.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImplImpl extends GenericDaoImpl<Ticket> implements TicketDao {
    public TicketDaoImplImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Ticket get(Long id) {
        return get(Ticket.class, id);
    }
}
