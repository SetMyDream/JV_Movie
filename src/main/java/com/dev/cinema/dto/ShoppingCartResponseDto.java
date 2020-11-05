package com.dev.cinema.dto;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingCartResponseDto {
    private Long userId;
    private List<Long> ticketsId;
}
