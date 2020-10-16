package com.dev.cinema.dao;

import com.dev.cinema.models.User;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    List<User> getAll();

    Optional<User> findByEmail(String email);
}
