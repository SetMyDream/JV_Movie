package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.models.ShoppingCart;
import com.dev.cinema.models.User;
import com.dev.cinema.util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> cartCriteriaQuery
                    = criteriaBuilder.createQuery(ShoppingCart.class);
            Root<ShoppingCart> root = cartCriteriaQuery.from(ShoppingCart.class);
            Predicate userIdPredicate = criteriaBuilder.equal(root.get("user"), user);
            cartCriteriaQuery.select(root).where(userIdPredicate);
            return session.createQuery(cartCriteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find shopping cart by user "
                    + user.getEmail(), e);
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
