package com.dev.cinema.dao;

import com.dev.cinema.models.ShoppingCart;
import com.dev.cinema.models.User;

public interface ShoppingCartDao extends GenericDao<ShoppingCart> {
    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
