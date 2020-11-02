package com.dev.cinema.controller;

import com.dev.cinema.dto.OrderRequestDto;
import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.mapper.OrderDtoMapper;
import com.dev.cinema.models.ShoppingCart;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
public class OrderController {
    private final OrderService orderService;
    private final OrderDtoMapper orderMapper;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, OrderDtoMapper orderMapper,
                           ShoppingCartService shoppingCartService, UserService userService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostMapping("/complete")
    public void completeOrder(
            @RequestBody OrderRequestDto orderRequestDto) {
        ShoppingCart shoppingCart = shoppingCartService
                .getByUser(userService.get(orderRequestDto.getId()));
        orderService.completeOrder(shoppingCart.getTickets(), shoppingCart.getUser());
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(@RequestParam String email) {
        return orderService.getOrderHistory(userService.findByEmail(email))
                .stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
