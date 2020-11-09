package com.dev.cinema.controller;

import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.mapper.OrderMapper;
import com.dev.cinema.models.ShoppingCart;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper,
                           ShoppingCartService shoppingCartService, UserService userService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostMapping("/complete")
    public void completeOrder(
            Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartService.getByUser(userService
                .findByEmail(principal.getUsername()).get());
        orderService.completeOrder(shoppingCart.getTickets(), shoppingCart.getUser());
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return orderService.getOrderHistory(userService.findByEmail(principal.getUsername())
                .get()).stream()
                .map(order -> orderMapper.toResponseDto(order))
                .collect(Collectors.toList());
    }
}
