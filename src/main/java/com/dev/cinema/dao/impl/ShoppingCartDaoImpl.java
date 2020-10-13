package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.models.ShoppingCart;
import com.dev.cinema.models.User;
import com.dev.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ShoppingCart> query = session.createQuery("FROM ShoppingCart sc"
                    + " join fetch sc.user"
                    + " left join fetch sc.tickets"
                    + " WHERE sc.user.id = :id", ShoppingCart.class);
            ((Query<?>) query).setParameter("id", user.getId());
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Shopping Cart with User  "
                    + user + "from DB", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t update shopping cart for user "
                    + shoppingCart.getUser().getEmail(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
