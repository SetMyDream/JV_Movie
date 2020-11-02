package com.dev.cinema.dto;

import lombok.Data;
import java.util.List;

@Data
public class ShoppingCartResponseDto {
    private Long userId;
    private List<Long> ticketsId;
}
