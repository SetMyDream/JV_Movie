package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.models.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<User> getAll() {
        return getAll(User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            Predicate emailPredicate = criteriaBuilder.equal(userRoot.get("email"), email);
            criteriaQuery.select(userRoot).where(emailPredicate);
            return session.createQuery(criteriaQuery).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find user by email" + email, e);
        }
    }

    @Override
    public User get(Long id) {
        return get(User.class, id);
    }
}
