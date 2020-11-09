package com.dev.cinema.service;

import com.dev.cinema.models.User;

public interface AuthenticationService {
    User register(String email, String password);
}
