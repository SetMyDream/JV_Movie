package com.dev.cinema.service;

import com.dev.cinema.models.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);
}
