package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.models.User;
import com.dev.cinema.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public List getAll() {
        return getAll(User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) throws AuthenticationException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            Predicate emailPredicate = criteriaBuilder.equal(userRoot.get("email"), email);
            criteriaQuery.select(userRoot).where(emailPredicate);
            return Optional.ofNullable(session.createQuery(criteriaQuery).getSingleResult());
        } catch (Exception e) {
            throw new AuthenticationException("Can`t find user by login", e);
        }
    }
}
