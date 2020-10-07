package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public interface GenericDao<T> {
    default T add(T entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert "
                    + entity.getClass().getSimpleName() + " to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    default List<T> getAll(Class<T> clazz) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery("from " + clazz.getSimpleName(), clazz);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all " + clazz.getSimpleName(), e);
        }
    }
}
