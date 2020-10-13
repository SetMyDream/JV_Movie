package com.dev.cinema.service;

import com.dev.cinema.models.Order;
import com.dev.cinema.models.Ticket;
import com.dev.cinema.models.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
