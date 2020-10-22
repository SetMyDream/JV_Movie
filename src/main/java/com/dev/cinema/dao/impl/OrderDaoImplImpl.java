package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.models.Order;
import com.dev.cinema.models.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImplImpl extends GenericDaoImpl<Order> implements OrderDao {
    public OrderDaoImplImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> orderQuery = session
                    .createQuery("SELECT DISTINCT u FROM Order u "
                                    + "LEFT JOIN FETCH u.tickets WHERE u.user.id = :userId ",
                            Order.class);
            orderQuery.setParameter("userId", user.getId());
            return orderQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find available orders associated with email = "
                    + user.getEmail(), e);
        }
    }
}
