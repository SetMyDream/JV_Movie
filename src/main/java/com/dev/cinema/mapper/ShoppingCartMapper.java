package com.dev.cinema.mapper;

import com.dev.cinema.dto.ShoppingCartResponseDto;
import com.dev.cinema.models.ShoppingCart;
import com.dev.cinema.models.Ticket;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto fromResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setTicketsId(shoppingCart.getTickets().stream()
                .map(Ticket::getId).collect(Collectors.toList()));
        responseDto.setUserId(shoppingCart.getUser().getId());
        return responseDto;
    }
}
