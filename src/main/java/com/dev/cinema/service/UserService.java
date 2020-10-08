package com.dev.cinema.service;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.models.User;

import java.util.Optional;

public interface UserService {
    User add(User user) throws AuthenticationException;

    Optional<User> findByEmail(String email) throws AuthenticationException;
}
