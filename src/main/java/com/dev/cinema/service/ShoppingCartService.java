package com.dev.cinema.service;

import com.dev.cinema.models.MovieSession;
import com.dev.cinema.models.ShoppingCart;
import com.dev.cinema.models.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
