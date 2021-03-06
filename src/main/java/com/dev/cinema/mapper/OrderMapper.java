package com.dev.cinema.mapper;

import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.dto.OrderServiceRequestDto;
import com.dev.cinema.models.Order;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private OrderService orderService;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public OrderMapper(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    public Order fromRequestDto(OrderServiceRequestDto orderServiceRequestDto) {
        Order order = new Order();
        order.setUser(userService.get(orderServiceRequestDto.getUserId()));
        order.setTickets(shoppingCartService.getByUser(order.getUser()).getTickets());
        orderService.completeOrder(order.getTickets(), order.getUser());
        return order;
    }

    public OrderResponseDto toResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTickets(order.getTickets());
        orderResponseDto.setUsername(order.getUser().getEmail());
        orderResponseDto.setOrderDate(order.getOrderDate());
        return orderResponseDto;
    }
}
